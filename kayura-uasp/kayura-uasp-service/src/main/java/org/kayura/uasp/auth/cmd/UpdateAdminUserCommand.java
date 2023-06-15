package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.user.AdminUserPayload;

public class UpdateAdminUserCommand extends Command {

  private String userId;
  private AdminUserPayload payload;

  public String getUserId() {
    return userId;
  }

  public UpdateAdminUserCommand setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public AdminUserPayload getPayload() {
    return payload;
  }

  public UpdateAdminUserCommand setPayload(AdminUserPayload payload) {
    this.payload = payload;
    return this;
  }
}
