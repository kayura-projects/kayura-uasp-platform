package org.kayura.uasp.file;

import org.kayura.type.EnumValue;

/** 所有类型:U个人;G群组;D部门;T租户共享;S系统共享; */
public enum OwnerTypes implements EnumValue {

  USER("U", "个人"),
  GROUP("G", "群组"),
  DEPART("D", "部门"),
  TENANT("T", "租户"),
  SYSTEM("S", "系统");

  private final String value;
  private final String name;

  OwnerTypes(final String value, String name) {
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
