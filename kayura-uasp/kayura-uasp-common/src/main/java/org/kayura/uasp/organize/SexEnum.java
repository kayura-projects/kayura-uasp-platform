package org.kayura.uasp.organize;

import org.kayura.type.EnumValue;

public enum SexEnum implements EnumValue {

  Man("M", "男"),
  Woman("W", "女"),
  Unknown("N", "未指明");

  private final String value;
  private final String name;

  SexEnum(final String value, String name) {
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
