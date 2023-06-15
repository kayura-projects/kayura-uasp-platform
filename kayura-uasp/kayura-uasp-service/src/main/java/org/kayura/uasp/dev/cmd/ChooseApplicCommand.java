package org.kayura.uasp.dev.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.applic.ApplicTypes;
import org.kayura.uasp.utils.OutputTypes;

public class ChooseApplicCommand extends Command {

  private OutputTypes output;
  private ApplicTypes type;
  private boolean notUasp;
  private String tenantId;
  private String companyId;
  private String userId;
  private Integer level;

  public OutputTypes getOutput() {
    return output;
  }

  public ChooseApplicCommand setOutput(OutputTypes output) {
    this.output = output;
    return this;
  }

  public ApplicTypes getType() {
    return type;
  }

  public ChooseApplicCommand setType(ApplicTypes type) {
    this.type = type;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public ChooseApplicCommand setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getCompanyId() {
    return companyId;
  }

  public ChooseApplicCommand setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public Integer getLevel() {
    return level;
  }

  public ChooseApplicCommand setLevel(Integer level) {
    this.level = level;
    return this;
  }

  public boolean isNotUasp() {
    return notUasp;
  }

  public ChooseApplicCommand setNotUasp(boolean notUasp) {
    this.notUasp = notUasp;
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public ChooseApplicCommand setUserId(String userId) {
    this.userId = userId;
    return this;
  }
}
