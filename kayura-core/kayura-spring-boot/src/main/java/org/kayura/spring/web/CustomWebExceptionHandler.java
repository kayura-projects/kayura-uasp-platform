/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
