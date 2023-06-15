package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.company.CompanyPayload;

public class CreateCompanyCommand extends Command {

  private CompanyPayload payload;

  public CompanyPayload getPayload() {
    return payload;
  }

  public CreateCompanyCommand setPayload(CompanyPayload payload) {
    this.payload = payload;
    return this;
  }
}

