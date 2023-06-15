package org.kayura.uasp.form;

import org.kayura.type.EnumValue;

/** 字段类型:M主键,F流转码,S状态码 */
public enum FieldUsage implements EnumValue {

  PrimaryKey("M", "主键"),
  Status("S", "状态码"),
  FlowCode("F", "流转码"),
  Data("D", "数据");

  private final String value;
  private final String name;

  FieldUsage(final String value, String name) {
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
