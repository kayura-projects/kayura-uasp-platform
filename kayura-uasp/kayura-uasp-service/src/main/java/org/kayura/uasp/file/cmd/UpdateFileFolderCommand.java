package org.kayura.uasp.file.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.file.FolderPayload;

public class UpdateFileFolderCommand extends Command {

  private String folderId;
  private FolderPayload payload;

  public String getFolderId() {
    return folderId;
  }

  public UpdateFileFolderCommand setFolderId(String folderId) {
    this.folderId = folderId;
    return this;
  }

  public FolderPayload getPayload() {
    return payload;
  }

  public UpdateFileFolderCommand setPayload(FolderPayload payload) {
    this.payload = payload;
    return this;
  }
}
