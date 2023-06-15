package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;

public class GetCompanyCommand extends Command {

  private String companyId;

  public String getCompanyId() {
    return companyId;
  }

  public GetCompanyCommand setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }
}
