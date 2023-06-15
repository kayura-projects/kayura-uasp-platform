package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.organize.DepartPayload;

public class UpdateDepartCommand extends Command {

  private String departId;
  private DepartPayload payload;

  public String getDepartId() {
    return departId;
  }

  public UpdateDepartCommand setDepartId(String departId) {
    this.departId = departId;
    return this;
  }

  public DepartPayload getPayload() {
    return payload;
  }

  public UpdateDepartCommand setPayload(DepartPayload payload) {
    this.payload = payload;
    return this;
  }
}
