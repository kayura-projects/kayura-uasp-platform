package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.common.IdPayload;

public class DeleteCompanyApplicCommand extends Command {

  private IdPayload payload;

  public IdPayload getPayload() {
    return payload;
  }

  public DeleteCompanyApplicCommand setPayload(IdPayload payload) {
    this.payload = payload;
    return this;
  }
}
