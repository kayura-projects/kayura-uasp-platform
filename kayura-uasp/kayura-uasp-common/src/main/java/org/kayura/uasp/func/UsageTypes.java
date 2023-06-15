package org.kayura.uasp.func;

import org.kayura.type.EnumValue;

public enum UsageTypes implements EnumValue {

  Admin("A", "管理"),
  Business("B", "业务"),
  Common("C", "通用");

  private final String value;
  private final String name;

  UsageTypes(final String value, String name) {
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
