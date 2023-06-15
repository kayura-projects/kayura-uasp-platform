package org.kayura.uasp.applibrary;

import org.kayura.type.EnumValue;

public enum UpdateModes implements EnumValue {

  ADD("A", "增量"),
  FULL("F", "整包");

  private final String value;
  private final String name;

  UpdateModes(final String value, String name) {
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
