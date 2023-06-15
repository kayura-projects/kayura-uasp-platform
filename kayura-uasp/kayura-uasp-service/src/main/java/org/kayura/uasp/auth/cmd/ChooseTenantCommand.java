package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.utils.OutputTypes;

public class ChooseTenantCommand extends Command {

  private OutputTypes outType;
  private boolean hasApp;
  private String appId;

  public OutputTypes getOutType() {
    return outType;
  }

  public ChooseTenantCommand setOutType(OutputTypes outType) {
    this.outType = outType;
    return this;
  }

  public boolean isHasApp() {
    return hasApp;
  }

  public ChooseTenantCommand setHasApp(boolean hasApp) {
    this.hasApp = hasApp;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public ChooseTenantCommand setAppId(String appId) {
    this.appId = appId;
    return this;
  }
}
