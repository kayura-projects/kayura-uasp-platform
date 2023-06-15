package org.kayura.uasp.dict;

import org.kayura.type.EnumValue;

/** 应用范围:D开发;B业务; */
public enum DictScopes implements EnumValue {

  Develop("D", "开发"),
  Business("B", "业务");

  private final String value;
  private final String name;

  DictScopes(final String value, String name) {
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
