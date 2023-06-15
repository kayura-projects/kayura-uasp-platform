package org.kayura.type;

public enum UsableStatus implements EnumValue {

  Valid("V", "启用"),
  Invalid("I", "禁用");

  private final String value;
  private final String name;

  UsableStatus(final String value, String name) {
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
