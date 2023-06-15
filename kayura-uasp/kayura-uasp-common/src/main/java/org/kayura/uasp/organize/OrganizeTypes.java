package org.kayura.uasp.organize;

import org.kayura.type.EnumValue;

public enum OrganizeTypes implements EnumValue {

  Company("C", "公司"),
  Depart("D", "部门"),
  Position("P", "岗位");

  private final String value;
  private final String name;

  OrganizeTypes(final String value, String name) {
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
