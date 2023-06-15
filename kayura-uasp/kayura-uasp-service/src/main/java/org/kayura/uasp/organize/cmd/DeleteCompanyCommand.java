package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.common.IdPayload;

public class DeleteCompanyCommand extends Command {

  private String companyId;
  private IdPayload payload;

  public String getCompanyId() {
    return companyId;
  }

  public DeleteCompanyCommand setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public IdPayload getPayload() {
    return payload;
  }

  public DeleteCompanyCommand setPayload(IdPayload payload) {
    this.payload = payload;
    return this;
  }
}
