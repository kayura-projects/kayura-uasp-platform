package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.company.CompanyApplicPayload;

import java.util.List;

public class CreateCompanyApplicCommand extends Command {

  private CompanyApplicPayload payload;
  private List<CompanyApplicPayload> payloads;

  public CompanyApplicPayload getPayload() {
    return payload;
  }

  public CreateCompanyApplicCommand setPayload(CompanyApplicPayload payload) {
    this.payload = payload;
    return this;
  }

  public List<CompanyApplicPayload> getPayloads() {
    return payloads;
  }

  public CreateCompanyApplicCommand setPayloads(List<CompanyApplicPayload> payloads) {
    this.payloads = payloads;
    return this;
  }
}
