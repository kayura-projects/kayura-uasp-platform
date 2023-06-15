package org.kayura.uasp.passport;

import org.kayura.type.EnumValue;

/** 状态 => pwd 密码; sms: 短信 */
public enum LoginTypes implements EnumValue {

  PWD("pwd", "密码"),
  SMS("sms", "短信");

  private final String value;
  private final String name;

  LoginTypes(final String value,
             final String name) {
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
