package org.kayura.uasp.basic.cmd;

import org.kayura.cmd.Command;

import java.util.List;

public class ImportModuleCommand extends Command {

  private String targetAppId;
  private String targetParentId;
  private List<String> sourceModuleIds;

  public String getTargetAppId() {
    return targetAppId;
  }

  public ImportModuleCommand setTargetAppId(String targetAppId) {
    this.targetAppId = targetAppId;
    return this;
  }

  public String getTargetParentId() {
    return targetParentId;
  }

  public ImportModuleCommand setTargetParentId(String targetParentId) {
    this.targetParentId = targetParentId;
    return this;
  }

  public List<String> getSourceModuleIds() {
    return sourceModuleIds;
  }

  public ImportModuleCommand setSourceModuleIds(List<String> sourceModuleIds) {
    this.sourceModuleIds = sourceModuleIds;
    return this;
  }
}
