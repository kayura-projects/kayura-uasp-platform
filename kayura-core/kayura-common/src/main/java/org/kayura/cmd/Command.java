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

package org.kayura.cmd;

import org.kayura.security.LoginUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {

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

  public Command setSource(CallerSource source) {
    this.source = source;
    return this;
  }

  public HttpServletRequest getRequest() {
    return request;
  }

  public Command setRequest(HttpServletRequest request) {
    this.request = request;
    return this;
  }

  public HttpServletResponse getResponse() {
    return response;
  }

  public Command setResponse(HttpServletResponse response) {
    this.response = response;
    return this;
  }

  public LoginUser getLoginUser() {
    return loginUser;
  }

  public Command setLoginUser(LoginUser loginUser) {
    this.loginUser = loginUser;
    return this;
  }

}
