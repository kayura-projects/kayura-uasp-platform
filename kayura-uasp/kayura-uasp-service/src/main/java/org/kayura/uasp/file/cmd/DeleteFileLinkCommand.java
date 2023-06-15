package org.kayura.uasp.file.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.common.IdPayload;

public class DeleteFileLinkCommand extends Command {

  private String linkId;
  private IdPayload payload;

  public IdPayload getPayload() {
    return payload;
  }

  public DeleteFileLinkCommand setPayload(IdPayload payload) {
    this.payload = payload;
    return this;
  }

  public String getLinkId() {
    return linkId;
  }

  public DeleteFileLinkCommand setLinkId(String linkId) {
    this.linkId = linkId;
    return this;
  }
}
