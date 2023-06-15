package org.kayura.uasp.auth.event;

import org.kayura.event.Event;
import org.kayura.security.LoginUser;

public class TokenLogoutEvent extends Event {

  private final LoginUser loginUser;
  private final String token;

  public TokenLogoutEvent(Object source, LoginUser loginUser, String token) {
    super(source);
    this.loginUser = loginUser;
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public LoginUser getLoginUser() {
    return loginUser;
  }
}
