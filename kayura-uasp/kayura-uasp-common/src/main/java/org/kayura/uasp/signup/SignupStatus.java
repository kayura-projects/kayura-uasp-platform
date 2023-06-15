package org.kayura.uasp.signup;

import org.kayura.type.EnumValue;

/** 状态:APPLYING 申请中,PASSED 允许,REFUSED 拒绝; */
public enum SignupStatus implements EnumValue {

  APPLYING("APPLYING", "密码"),
  PASSED("PASSED", "允许"),
  REFUSED("REFUSED", "拒绝");

  private final String value;
  private final String name;

  SignupStatus(final String value,
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
