package org.kayura.uasp.func;

import org.kayura.type.EnumValue;

public enum ModuleTypes implements EnumValue {

  Category("C", "分类"),
  Divide("D", "分隔符"),
  Module("M", "模块");

  private final String value;
  private final String name;

  ModuleTypes(final String value, String name) {
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
