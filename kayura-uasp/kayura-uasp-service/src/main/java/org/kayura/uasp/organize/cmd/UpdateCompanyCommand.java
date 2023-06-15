package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.company.CompanyPayload;

public class UpdateCompanyCommand extends Command {

  private String companyId;
  private CompanyPayload payload;

  public String getCompanyId() {
    return companyId;
  }

  public UpdateCompanyCommand setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public CompanyPayload getPayload() {
    return payload;
  }

  public UpdateCompanyCommand setPayload(CompanyPayload payload) {
    this.payload = payload;
    return this;
  }
}
