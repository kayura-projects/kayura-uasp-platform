package org.kayura.uasp.company;

import org.kayura.type.EnumValue;

/** 类型:TENANT租用公司,CHILD子公司;CONTRACT往来公司; */
public enum CompanyTypes implements EnumValue {

  Tenant("TENANT", "租用公司"),
  Child("CHILD", "子公司"),
  Contact("CONTACT", "往来公司");

  private final String value;
  private final String name;

  CompanyTypes(final String value, String name) {
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
