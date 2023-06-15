package org.kayura.uasp.ops.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.applibrary.AppStorePayload;

public class UpdateAppStoreCommand extends Command {

  private String releaseId;
  private AppStorePayload payload;

  public String getReleaseId() {
    return releaseId;
  }

  public UpdateAppStoreCommand setReleaseId(String releaseId) {
    this.releaseId = releaseId;
    return this;
  }

  public AppStorePayload getPayload() {
    return payload;
  }

  public UpdateAppStoreCommand setPayload(AppStorePayload payload) {
    this.payload = payload;
    return this;
  }
}
