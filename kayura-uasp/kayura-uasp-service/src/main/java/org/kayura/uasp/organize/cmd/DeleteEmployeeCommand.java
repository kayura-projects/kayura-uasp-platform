package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.common.IdPayload;

public class DeleteEmployeeCommand extends Command {

  private String employeeId;
  private IdPayload payload;

  public String getEmployeeId() {
    return employeeId;
  }

  public DeleteEmployeeCommand setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
    return this;
  }

  public IdPayload getPayload() {
    return payload;
  }

  public DeleteEmployeeCommand setPayload(IdPayload payload) {
    this.payload = payload;
    return this;
  }
}
