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
