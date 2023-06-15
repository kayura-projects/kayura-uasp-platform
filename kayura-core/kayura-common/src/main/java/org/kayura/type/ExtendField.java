package org.kayura.type;

public class ExtendField {

  private String key;
  private String value;
  private String remark;

  public static ExtendField create() {
    return new ExtendField();
  }

  public static ExtendField clone(ExtendField source) {
    return create().setKey(source.key).setValue(source.value).setRemark(source.remark);
  }

  public String getKey() {
    return key;
  }

  public ExtendField setKey(String key) {
    this.key = key;
    return this;
  }

  public String getValue() {
    return value;
  }

  public ExtendField setValue(String value) {
    this.value = value;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public ExtendField setRemark(String remark) {
    this.remark = remark;
    return this;
  }

}
