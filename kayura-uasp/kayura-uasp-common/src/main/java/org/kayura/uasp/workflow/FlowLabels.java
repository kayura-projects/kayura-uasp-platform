package org.kayura.uasp.workflow;

import java.util.ArrayList;

public class FlowLabels extends ArrayList<FlowLabel> {

  public FlowLabels addLabel(String code, String name) {
    if (this.stream().noneMatch(x -> code.equals(x.getCode()))) {
      this.add(FlowLabel.create().setCode(code).setName(name));
    }
    return this;
  }

}
