package org.kayura.uasp.auth.event;

import org.kayura.event.Event;
import org.kayura.security.LoginUser;

public class LoginSuccessEvent extends Event {

  private final LoginUser loginUser;

  public LoginSuccessEvent(Object source, LoginUser loginUser) {
    super(source);
    this.loginUser = loginUser;
  }

  public LoginUser getLoginUser() {
    return loginUser;
  }

}
