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

package org.kayura.cmd;

import org.kayura.security.LoginUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ApiCommand implements ICommand{

  @JsonIgnore
  private CallerSource source;
  @JsonIgnore
  private LoginUser loginUser;
  @JsonIgnore
  private HttpServletRequest request;
  @JsonIgnore
  private HttpServletResponse response;

  public CallerSource getSource() {
    return source;
  }

  public ApiCommand setSource(CallerSource source) {
    this.source = source;
    return this;
  }

  public HttpServletRequest getRequest() {
    return request;
  }

  public ApiCommand setRequest(HttpServletRequest request) {
    this.request = request;
    return this;
  }

  public HttpServletResponse getResponse() {
    return response;
  }

  public ApiCommand setResponse(HttpServletResponse response) {
    this.response = response;
    return this;
  }

  public LoginUser getLoginUser() {
    return loginUser;
  }

  public ApiCommand setLoginUser(LoginUser loginUser) {
    this.loginUser = loginUser;
    return this;
  }

}
