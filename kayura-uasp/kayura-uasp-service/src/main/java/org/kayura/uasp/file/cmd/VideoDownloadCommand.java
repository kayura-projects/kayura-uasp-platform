package org.kayura.uasp.file.cmd;

import org.kayura.cmd.Command;

public class VideoDownloadCommand extends Command {

  private String fileLinkId;

  public String getFileLinkId() {
    return fileLinkId;
  }

  public VideoDownloadCommand setFileLinkId(String fileLinkId) {
    this.fileLinkId = fileLinkId;
    return this;
  }
}
