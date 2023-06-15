package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.organize.PositionPayload;

public class CreatePositionCommand extends Command {

  private PositionPayload payload;

  public PositionPayload getPayload() {
    return payload;
  }

  public CreatePositionCommand setPayload(PositionPayload payload) {
    this.payload = payload;
    return this;
  }
}
