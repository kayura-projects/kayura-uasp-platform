package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.UserTypes;
import org.kayura.uasp.common.IdPayload;

public class DeleteUserCommand extends Command {

  private UserTypes userType;
  private IdPayload payload;

  public IdPayload getPayload() {
    return payload;
  }

  public DeleteUserCommand setPayload(IdPayload payload) {
    this.payload = payload;
    return this;
  }

  public UserTypes getUserType() {
    return userType;
  }

  public DeleteUserCommand setUserType(UserTypes userType) {
    this.userType = userType;
    return this;
  }
}
