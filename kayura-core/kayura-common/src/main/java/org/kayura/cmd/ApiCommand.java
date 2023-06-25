/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 www.kayura.cn
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
