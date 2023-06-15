package org.kayura.uasp.file.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.file.MoveFilePayload;

public class MoveFileLinkCommand extends Command {

  private MoveFilePayload payload;

  public MoveFilePayload getPayload() {
    return payload;
  }

  public MoveFileLinkCommand setPayload(MoveFilePayload payload) {
    this.payload = payload;
    return this;
  }
}
