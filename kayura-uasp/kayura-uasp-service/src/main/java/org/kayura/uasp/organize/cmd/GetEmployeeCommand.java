package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;

public class GetEmployeeCommand extends Command {

  private String employeeId;

  public String getEmployeeId() {
    return employeeId;
  }

  public GetEmployeeCommand setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
    return this;
  }
}
