package org.kayura.uasp.func;

import org.kayura.type.EnumValue;

public enum ActionTypes implements EnumValue {

  Func("C", "功能"),
  Data("D", "数据");

  private final String value;
  private final String name;

  ActionTypes(final String value, String name) {
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
