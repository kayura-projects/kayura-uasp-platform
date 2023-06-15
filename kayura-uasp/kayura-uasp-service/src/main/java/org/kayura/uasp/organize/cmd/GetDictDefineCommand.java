package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;

public class GetDictDefineCommand extends Command {

  private String defineId;

  public String getDefineId() {
    return defineId;
  }

  public GetDictDefineCommand setDefineId(String defineId) {
    this.defineId = defineId;
    return this;
  }
}
