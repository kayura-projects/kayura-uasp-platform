package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.DataStatus;
import org.kayura.uasp.utils.OutputTypes;

public class SelectOrganizeTreeCommand extends Command {

  private OutputTypes output;
  private String companyId;
  private Integer level;
  private DataStatus status;

  public OutputTypes getOutput() {
    return output;
  }

  public SelectOrganizeTreeCommand setOutput(OutputTypes output) {
    this.output = output;
    return this;
  }

  public String getCompanyId() {
    return companyId;
  }

  public SelectOrganizeTreeCommand setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public Integer getLevel() {
    return level;
  }

  public SelectOrganizeTreeCommand setLevel(Integer level) {
    this.level = level;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public SelectOrganizeTreeCommand setStatus(DataStatus status) {
    this.status = status;
    return this;
  }
}
