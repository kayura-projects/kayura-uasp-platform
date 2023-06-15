package org.kayura.uasp.privilege;

import org.kayura.type.EnumValue;

/** 权限类型:T租户,R角色,U用户,C往来公司 */
public enum PrivilegeTypes implements EnumValue {

  Role("R", "角色"),
  User("U", "用户"),
  Company("C", "公司");

  private final String value;
  private final String name;

  PrivilegeTypes(final String value, String name) {
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
