package org.kayura.uasp.workflow;

import java.util.ArrayList;
import java.util.List;

public class FormulaGroup {

  private String lo;
  private List<FormulaItem> items;

  public String getLo() {
    return lo;
  }

  public FormulaGroup setLo(String lo) {
    this.lo = lo;
    return this;
  }

  public List<FormulaItem> getItems() {
    return items;
  }

  public FormulaGroup setItems(List<FormulaItem> items) {
    this.items = items;
    return this;
  }

  public FormulaGroup addItem(FormulaItem item) {
    if (this.items == null) {
      this.items = new ArrayList<>();
    }
    this.items.add(item);
    return this;
  }
}
