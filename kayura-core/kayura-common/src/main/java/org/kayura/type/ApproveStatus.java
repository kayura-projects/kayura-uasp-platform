package org.kayura.type;

public enum ApproveStatus implements EnumValue {

  Draft("D", "草稿"),
  Audit("A", "审核中"),
  Success("S", "已通过"),
  Reject("R", "未通过"),
  Invalid("I", "已作废");

  private final String value;
  private final String name;

  ApproveStatus(final String value, String name) {
    this.value = value;
    this.name = name;
  }

  @Override
  public String toString() {
    return this.value;
  }

  @Override
  public String getName() {
    return name;
  }
}
