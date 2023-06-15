package org.kayura.uasp.basic.cmd;

import org.kayura.cmd.Command;

import java.util.Map;

public class ChooseDictItemCommand extends Command {

  private String define;
  private String tenantId;
  private Map<String, String> defines;

  public String getDefine() {
    return define;
  }

  public ChooseDictItemCommand setDefine(String define) {
    this.define = define;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public ChooseDictItemCommand setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public Map<String, String> getDefines() {
    return defines;
  }

  public ChooseDictItemCommand setDefines(Map<String, String> defines) {
    this.defines = defines;
    return this;
  }
}
