package org.kayura.uasp.role;

import org.kayura.type.EnumValue;

/** 类型:FN功能角色;WF流程角色;DT数据角色; */
public enum RoleTypes implements EnumValue {

  FUNC("FN", "功能角色"),
  WORK_FLOW("WF", "流程角色"),
  DATA("DT", "数据角色");

  private final String value;
  private final String name;

  RoleTypes(final String value, String name) {
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
