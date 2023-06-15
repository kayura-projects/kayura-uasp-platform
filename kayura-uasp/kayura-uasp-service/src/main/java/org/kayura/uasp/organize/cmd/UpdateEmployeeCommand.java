package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.organize.EmployeePayload;

public class UpdateEmployeeCommand extends Command {

  private String employeeId;
  private EmployeePayload payload;

  public String getEmployeeId() {
    return employeeId;
  }

  public UpdateEmployeeCommand setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
    return this;
  }

  public EmployeePayload getPayload() {
    return payload;
  }

  public UpdateEmployeeCommand setPayload(EmployeePayload payload) {
    this.payload = payload;
    return this;
  }
}
