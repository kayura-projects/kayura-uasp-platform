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

package org.kayura.spring.web;

import org.kayura.type.HttpResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.nio.file.AccessDeniedException;
import java.util.stream.Collectors;

public class CustomWebExceptionHandler extends AbstractHandlerExceptionResolver {

  private final static Log LOGGER = LogFactory.getLog(CustomWebExceptionHandler.class);

  private ObjectMapper objectMapper;
  private boolean isDebug;

  @Override
  protected ModelAndView doResolveException(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                                            Object handler, @NotNull Exception ex) {

    HttpResult result;
    PrintWriter writer = null;
    try {
      if (ex instanceof BindingResult) {
        BindingResult bindingResult;
        if (ex instanceof MethodArgumentNotValidException) {
          bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
        } else {
          bindingResult = ((BindingResult) ex);
        }
        String message = bindingResult.getAllErrors().stream().map(m -> {
          StringBuilder sb = new StringBuilder();
          if (m instanceof FieldError) {
            sb.append("[").append(((FieldError) m).getField()).append("] ");
          }
          sb.append(m.getDefaultMessage());
          return sb;
        }).collect(Collectors.joining(","));
        result = HttpResult.error(message);
      } else if ("org.activiti.engine.ActivitiException".equals(ex.getClass().getName())) {
        if (ex.getCause() != null) {
          result = HttpResult.error(ex.getCause().getMessage());
        } else {
          result = HttpResult.error(ex.getMessage());
        }
      } else if (ex instanceof AccessDeniedException) {
        result = HttpResult.error(ex.getMessage());
      } else if (ex instanceof HttpMessageNotReadableException) {
        result = HttpResult.error("缺少必需的请求数据。");
      } else if (ex instanceof RuntimeException) {
        result = HttpResult.error(ex.getMessage());
      } else {
        result = HttpResult.error(isDebug ? ex.getMessage() : "系统无法提供请求服务。");
      }
      response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
      response.setCharacterEncoding("UTF-8");
      writer = response.getWriter();
      writer.println(objectMapper.writeValueAsString(result));
      writer.flush();
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    } finally {
      if (writer != null) {
        writer.close();
      }
    }

    return new ModelAndView();
  }

  public void setObjectMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public void setDebug(boolean debug) {
    isDebug = debug;
  }
}
