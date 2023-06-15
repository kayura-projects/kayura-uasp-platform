package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.organize.PositionPayload;

public class UpdatePositionCommand extends Command {

  private String positionId;
  private PositionPayload payload;

  public String getPositionId() {
    return positionId;
  }

  public UpdatePositionCommand setPositionId(String positionId) {
    this.positionId = positionId;
    return this;
  }

  public PositionPayload getPayload() {
    return payload;
  }

  public UpdatePositionCommand setPayload(PositionPayload payload) {
    this.payload = payload;
    return this;
  }
}
