package org.kayura.uasp.dev.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.applic.ApplicPayload;

public class UpdateApplicCommand extends Command {

  private String appId;
  private ApplicPayload payload;

  public String getAppId() {
    return appId;
  }

  public UpdateApplicCommand setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public ApplicPayload getPayload() {
    return payload;
  }

  public UpdateApplicCommand setPayload(ApplicPayload payload) {
    this.payload = payload;
    return this;
  }
}
