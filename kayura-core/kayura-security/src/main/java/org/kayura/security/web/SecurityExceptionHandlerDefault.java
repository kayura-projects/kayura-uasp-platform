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
