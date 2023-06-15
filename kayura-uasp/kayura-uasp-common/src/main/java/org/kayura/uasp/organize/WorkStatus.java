package org.kayura.uasp.organize;

import org.kayura.type.EnumValue;

/** 工作状态:Active在职,Suspend停职,Leave离职; */
public enum WorkStatus implements EnumValue {

  Active("A", "在职"),
  Suspend("S", "停职"),
  Leave("L", "离职");

  private final String value;
  private final String name;

  WorkStatus(final String value, String name) {
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
