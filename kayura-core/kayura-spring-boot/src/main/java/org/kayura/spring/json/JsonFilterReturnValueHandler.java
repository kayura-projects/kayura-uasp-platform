/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
 -----------------------------------------------------------------------------*/

package org.kayura.spring.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonFilterReturnValueHandler implements HandlerMethodReturnValueHandler, BeanPostProcessor {

  @SuppressWarnings("unused")
  private static final Log LOGGER = LogFactory.getLog(JsonFilterReturnValueHandler.class);
  private final ObjectMapper objectMapper;

  public JsonFilterReturnValueHandler(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public boolean supportsReturnType(MethodParameter returnType) {
    return returnType.hasMethodAnnotation(DataFilter.class) || returnType.hasMethodAnnotation(DataFilters.class);
  }

  @Override
  public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest) throws Exception {

    mavContainer.setRequestHandled(Boolean.TRUE);

    ObjectMapper objectMapper = this.objectMapper.copy();
    JacksonJsonFilter jsonFilter = new JacksonJsonFilter();

    Annotation[] annotations = returnType.getMethodAnnotations();
    Arrays.stream(annotations).forEach(annotation -> {
      if (annotation instanceof DataFilter) {
        DataFilter resultFilter = (DataFilter) annotation;
        jsonFilter.filter(resultFilter);
        objectMapper.addMixIn(resultFilter.type(), JacksonJsonFilter.class);
      } else if (annotation instanceof DataFilters) {
        Arrays.stream(((DataFilters) annotation).value()).forEach(resultFilter -> {
          jsonFilter.filter(resultFilter);
          objectMapper.addMixIn(resultFilter.type(), JacksonJsonFilter.class);
        });
      }
    });

    HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
    assert response != null;
    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    String json = objectMapper.writer(jsonFilter).writeValueAsString(returnValue);
    response.getWriter().write(json);
  }

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

    if (bean instanceof RequestMappingHandlerAdapter) {

      List<HandlerMethodReturnValueHandler> list = ((RequestMappingHandlerAdapter) bean).getReturnValueHandlers();
      assert list != null;
      List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>(list);

      HandlerMethodReturnValueHandler jsonHandler = handlers.stream()
        .filter(handler -> handler instanceof JsonFilterReturnValueHandler).findFirst().orElse(null);

      if (jsonHandler != null) {
        handlers.remove(jsonHandler);
        handlers.add(0, jsonHandler);
        ((RequestMappingHandlerAdapter) bean).setReturnValueHandlers(handlers);
      }
    }
    return bean;
  }
}
