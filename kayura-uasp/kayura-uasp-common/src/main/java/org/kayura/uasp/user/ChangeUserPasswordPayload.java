package org.kayura.uasp.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ChangeUserPasswordPayload {

  @NotBlank
  private String userId;
  @NotNull
  private ChangeModes changeMode;
  private String password;
  @NotNull
  private NotifyModes notifyMode;

  public static ChangeUserPasswordPayload create() {
    return new ChangeUserPasswordPayload();
  }

  public String getUserId() {
    return userId;
  }

  public ChangeUserPasswordPayload setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public ChangeModes getChangeMode() {
    return changeMode;
  }

  public ChangeUserPasswordPayload setChangeMode(ChangeModes changeMode) {
    this.changeMode = changeMode;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public ChangeUserPasswordPayload setPassword(String password) {
    this.password = password;
    return this;
  }

  public NotifyModes getNotifyMode() {
    return notifyMode;
  }

  public ChangeUserPasswordPayload setNotifyMode(NotifyModes notifyMode) {
    this.notifyMode = notifyMode;
    return this;
  }
}
