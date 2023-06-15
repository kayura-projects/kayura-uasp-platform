package org.kayura.uasp.organize;

import org.kayura.type.EnumValue;

public enum JobStatus implements EnumValue {

  Active("A", "在岗"),
  Leave("L", "离岗");

  private final String value;
  private final String name;

  JobStatus(final String value, String name) {
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
