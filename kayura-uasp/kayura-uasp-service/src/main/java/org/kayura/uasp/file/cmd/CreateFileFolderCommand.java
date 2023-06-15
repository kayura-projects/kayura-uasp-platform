package org.kayura.uasp.file.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.file.FolderPayload;

public class CreateFileFolderCommand extends Command {

  private FolderPayload payload;

  public FolderPayload getPayload() {
    return payload;
  }

  public CreateFileFolderCommand setPayload(FolderPayload payload) {
    this.payload = payload;
    return this;
  }
}
