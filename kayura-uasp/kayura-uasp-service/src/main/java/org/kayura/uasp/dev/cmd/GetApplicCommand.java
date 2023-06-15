package org.kayura.uasp.dev.cmd;

import org.kayura.cmd.Command;

public class GetApplicCommand extends Command {

  private String appId;

  public String getAppId() {
    return appId;
  }

  public GetApplicCommand setAppId(String appId) {
    this.appId = appId;
    return this;
  }
}
