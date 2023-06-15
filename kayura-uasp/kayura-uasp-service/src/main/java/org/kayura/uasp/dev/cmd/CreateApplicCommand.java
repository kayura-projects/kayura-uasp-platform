package org.kayura.uasp.dev.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.applic.ApplicPayload;

public class CreateApplicCommand extends Command {

  private ApplicPayload payload;

  public ApplicPayload getPayload() {
    return payload;
  }

  public CreateApplicCommand setPayload(ApplicPayload payload) {
    this.payload = payload;
    return this;
  }
}
