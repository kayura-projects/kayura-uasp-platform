package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.organize.EmployeePayload;

public class CreateEmployeeCommand extends Command {

  private EmployeePayload payload;

  public EmployeePayload getPayload() {
    return payload;
  }

  public CreateEmployeeCommand setPayload(EmployeePayload payload) {
    this.payload = payload;
    return this;
  }
}
