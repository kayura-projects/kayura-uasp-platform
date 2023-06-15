package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.utils.OutputTypes;

import java.util.Set;

public class ChooseDepartCommand extends Command {

  private OutputTypes output;
  private String companyId;
  private Set<String> exclusions;

  public String getCompanyId() {
    return companyId;
  }

  public ChooseDepartCommand setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public Set<String> getExclusions() {
    return exclusions;
  }

  public ChooseDepartCommand setExclusions(Set<String> exclusions) {
    this.exclusions = exclusions;
    return this;
  }

  public OutputTypes getOutput() {
    return output;
  }

  public ChooseDepartCommand setOutput(OutputTypes output) {
    this.output = output;
    return this;
  }
}
