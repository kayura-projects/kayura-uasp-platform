package org.kayura.uasp.ops.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.applibrary.AppStorePayload;

public class CreateAppStoreCommand extends Command {

  private AppStorePayload payload;

  public AppStorePayload getPayload() {
    return payload;
  }

  public CreateAppStoreCommand setPayload(AppStorePayload payload) {
    this.payload = payload;
    return this;
  }
}
