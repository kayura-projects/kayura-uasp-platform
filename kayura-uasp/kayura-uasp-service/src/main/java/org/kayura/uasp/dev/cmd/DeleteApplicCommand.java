package org.kayura.uasp.dev.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.common.IdPayload;

public class DeleteApplicCommand extends Command {

  private String appId;
  private IdPayload payload;

  public String getAppId() {
    return appId;
  }

  public DeleteApplicCommand setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public IdPayload getPayload() {
    return payload;
  }

  public DeleteApplicCommand setPayload(IdPayload payload) {
    this.payload = payload;
    return this;
  }
}
