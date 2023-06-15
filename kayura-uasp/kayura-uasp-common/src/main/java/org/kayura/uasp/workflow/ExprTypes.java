package org.kayura.uasp.workflow;

import org.kayura.type.EnumValue;

/** 条件类型:NONE无,INNER内置,CUSTOM自定义 */
public enum ExprTypes implements EnumValue {

  NONE("NONE", "无条件"),
  INNER("INNER", "内置条件"),
  CUSTOM("CUSTOM", "自定义条件");

  private final String value;
  private final String name;

  ExprTypes(final String value, String name) {
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
