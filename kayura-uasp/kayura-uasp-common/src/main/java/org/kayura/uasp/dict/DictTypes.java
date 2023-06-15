package org.kayura.uasp.dict;

import org.kayura.type.EnumValue;

/** 定义类型：C字典分类,I字典项; */
public enum DictTypes implements EnumValue {

  Category("C", "字典分类"),
  Item("I", "字典项");

  private final String value;
  private final String name;

  DictTypes(final String value, String name) {
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
