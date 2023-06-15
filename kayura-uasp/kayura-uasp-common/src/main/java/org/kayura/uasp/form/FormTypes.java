package org.kayura.uasp.form;

import org.kayura.type.EnumValue;

/** 表单类型:B 业务表单；C 自定义表单； */
public enum FormTypes implements EnumValue {

  B("B", "业务表单"),
  C("C", "自定义表单");

  private final String value;
  private final String name;

  FormTypes(final String value, String name) {
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
