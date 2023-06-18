/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
 -----------------------------------------------------------------------------*/

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
