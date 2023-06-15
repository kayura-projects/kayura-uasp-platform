package org.kayura.uasp.dict;

import org.kayura.type.EnumValue;

/** 数据类型:L列表;T树型; */
public enum DataTypes implements EnumValue {

  List("L", "列表"),
  Tree("T", "树型");

  private final String value;
  private final String name;

  DataTypes(final String value, String name) {
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
