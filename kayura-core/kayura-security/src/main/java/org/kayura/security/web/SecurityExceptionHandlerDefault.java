/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ 版权所属 2019 Liang.Xia 及 原作者
 ~ 您可以在 Apache License 2.0 版下获得许可副本，
 ~ 同时必须保证分发的本软件是在“原始”的基础上分发的。
 ~ 除非适用法律要求或书面同意。
 ~
 ~ http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ 请参阅许可证中控制权限和限制的特定语言。
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

package org.kayura.security.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kayura.type.HttpResult;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SecurityExceptionHandlerDefault implements SecurityExceptionHandler {

  private final ObjectMapper objectMapper;

  public SecurityExceptionHandlerDefault(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
    throws IOException {

    HttpResult httpResult;
    if (accessDeniedException instanceof PermissionDeniedException) {
      httpResult = HttpResult.error(HttpResult.SC_FORBIDDEN, accessDeniedException.getMessage());
    } else if (accessDeniedException != null) {
      httpResult = HttpResult.error(HttpResult.SC_UNAUTHORIZED, accessDeniedException.getMessage());
    } else {
      httpResult = HttpResult.error(response.getStatus(), SecurityConstants.MSG_ACCESS_DENIED);
    }

    outputMessage(response, httpResult);
  }

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

    HttpResult httpResult = HttpResult.error(HttpResult.SC_UNAUTHORIZED, SecurityConstants.MSG_ACCESS_DENIED);
    outputMessage(response, httpResult);
  }

  public void outputMessage(HttpServletResponse response, HttpResult httpResult) throws IOException {

    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(httpResult.getCode());
    PrintWriter writer = response.getWriter();
    writer.println(objectMapper.writeValueAsString(httpResult));
    writer.flush();
    writer.close();
  }
}
