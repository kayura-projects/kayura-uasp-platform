package org.kayura.uasp.applic;

import org.kayura.type.EnumValue;

public enum ApplicTypes implements EnumValue {

  PC("PC", "Web应用"),
  WX("WX", "微信应用"),
  APP("APP", "移动应用");

  private final String value;
  private final String name;

  ApplicTypes(final String value, String name) {
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
