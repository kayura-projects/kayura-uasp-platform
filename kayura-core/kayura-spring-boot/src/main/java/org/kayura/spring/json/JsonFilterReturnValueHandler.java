/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
