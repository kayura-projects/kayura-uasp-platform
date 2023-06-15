package org.kayura.uasp.auth.event;

import org.kayura.event.Event;
import org.kayura.uasp.auth.entity.UserEntity;

public class PasswordChangedEvent extends Event {

  private String userId;
  private UserEntity user;

  public PasswordChangedEvent(Object source, String userId) {
    super(source);
    this.userId = userId;
  }

  public PasswordChangedEvent(Object source, UserEntity user) {
    super(source);
    this.user = user;
  }

  public String getUserId() {
    return userId;
  }

  public UserEntity getUser() {
    return user;
  }

}
