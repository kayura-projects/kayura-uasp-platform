package org.kayura.uasp.ops.cmd;

import org.kayura.cmd.Command;

public class GetAppStoreCommand extends Command {

  private String releaseId;

  public String getReleaseId() {
    return releaseId;
  }

  public GetAppStoreCommand setReleaseId(String releaseId) {
    this.releaseId = releaseId;
    return this;
  }
}
