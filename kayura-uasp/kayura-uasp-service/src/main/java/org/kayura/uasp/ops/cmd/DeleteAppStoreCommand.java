package org.kayura.uasp.ops.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.common.IdPayload;

public class DeleteAppStoreCommand extends Command {

  private String releaseId;
  private IdPayload payload;

  public String getReleaseId() {
    return releaseId;
  }

  public DeleteAppStoreCommand setReleaseId(String releaseId) {
    this.releaseId = releaseId;
    return this;
  }

  public IdPayload getPayload() {
    return payload;
  }

  public DeleteAppStoreCommand setPayload(IdPayload payload) {
    this.payload = payload;
    return this;
  }
}
