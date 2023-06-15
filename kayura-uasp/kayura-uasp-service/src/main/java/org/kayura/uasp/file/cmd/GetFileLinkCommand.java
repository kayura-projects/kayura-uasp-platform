package org.kayura.uasp.file.cmd;

import org.kayura.cmd.Command;

public class GetFileLinkCommand extends Command {

  private String linkId;

  public String getLinkId() {
    return linkId;
  }

  public GetFileLinkCommand setLinkId(String linkId) {
    this.linkId = linkId;
    return this;
  }
}
