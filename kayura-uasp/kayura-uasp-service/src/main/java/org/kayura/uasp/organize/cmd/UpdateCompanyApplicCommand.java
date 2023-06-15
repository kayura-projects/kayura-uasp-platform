package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.company.CompanyApplicPayload;

public class UpdateCompanyApplicCommand extends Command {

  private CompanyApplicPayload payload;

  public CompanyApplicPayload getPayload() {
    return payload;
  }

  public UpdateCompanyApplicCommand setPayload(CompanyApplicPayload payload) {
    this.payload = payload;
    return this;
  }
}
