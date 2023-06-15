package org.kayura.uasp.workflow;

public class FormulaItem {

  private String lo;
  private String name;
  private String type;
  private String ro;
  private String value;

  public String getLo() {
    return lo;
  }

  public FormulaItem setLo(String lo) {
    this.lo = lo;
    return this;
  }

  public String getName() {
    return name;
  }

  public FormulaItem setName(String name) {
    this.name = name;
    return this;
  }

  public String getType() {
    return type;
  }

  public FormulaItem setType(String type) {
    this.type = type;
    return this;
  }

  public String getRo() {
    return ro;
  }

  public FormulaItem setRo(String ro) {
    this.ro = ro;
    return this;
  }

  public String getValue() {
    return value;
  }

  public FormulaItem setValue(String value) {
    this.value = value;
    return this;
  }
}
