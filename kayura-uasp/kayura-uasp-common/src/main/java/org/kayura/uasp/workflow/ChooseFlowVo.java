package org.kayura.uasp.workflow;

public class ChooseFlowVo {

  private String processKey;
  private String displayName;
  private String description;
  private Boolean primary;
  private Integer lastVersion;

  public static ChooseFlowVo create() {
    return new ChooseFlowVo();
  }

  public String getProcessKey() {
    return processKey;
  }

  public ChooseFlowVo setProcessKey(String processKey) {
    this.processKey = processKey;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public ChooseFlowVo setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public ChooseFlowVo setDescription(String description) {
    this.description = description;
    return this;
  }

  public Boolean getPrimary() {
    return primary;
  }

  public ChooseFlowVo setPrimary(Boolean primary) {
    this.primary = primary;
    return this;
  }

  public Integer getLastVersion() {
    return lastVersion;
  }

  public ChooseFlowVo setLastVersion(Integer lastVersion) {
    this.lastVersion = lastVersion;
    return this;
  }
}
