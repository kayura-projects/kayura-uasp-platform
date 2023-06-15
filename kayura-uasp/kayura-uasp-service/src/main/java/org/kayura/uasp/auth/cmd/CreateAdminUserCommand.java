package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.user.AdminUserPayload;

public class CreateAdminUserCommand extends Command {

  private AdminUserPayload payload;

  public AdminUserPayload getPayload() {
    return payload;
  }

  public CreateAdminUserCommand setPayload(AdminUserPayload payload) {
    this.payload = payload;
    return this;
  }
}
