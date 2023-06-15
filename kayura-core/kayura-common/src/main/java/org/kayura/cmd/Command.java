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
