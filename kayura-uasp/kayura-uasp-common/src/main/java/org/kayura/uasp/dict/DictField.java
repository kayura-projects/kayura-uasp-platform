package org.kayura.uasp.dict;

public class DictField {

  private Integer index;
  private String displayName;
  private String fieldName;

  public Integer getIndex() {
    return index;
  }

  public DictField setIndex(Integer index) {
    this.index = index;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public DictField setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public String getFieldName() {
    return fieldName;
  }

  public DictField setFieldName(String fieldName) {
    this.fieldName = fieldName;
    return this;
  }
}
