package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.organize.DepartPayload;

public class CreateDepartCommand extends Command {

  private DepartPayload payload;

  public DepartPayload getPayload() {
    return payload;
  }

  public CreateDepartCommand setPayload(DepartPayload payload) {
    this.payload = payload;
    return this;
  }
}
