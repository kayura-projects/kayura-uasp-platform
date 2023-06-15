package org.kayura.uasp.form;

public class FormFieldVo {

  private String formId;
  private String fieldName;
  private String propName;
  private String displayName;
  private Integer sort;
  private String dataType;
  private FieldUsage usage;

  public String getFormId() {
    return formId;
  }

  public FormFieldVo setFormId(String formId) {
    this.formId = formId;
    return this;
  }

  public String getFieldName() {
    return fieldName;
  }

  public FormFieldVo setFieldName(String fieldName) {
    this.fieldName = fieldName;
    return this;
  }

  public String getPropName() {
    return propName;
  }

  public FormFieldVo setPropName(String propName) {
    this.propName = propName;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public FormFieldVo setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public FormFieldVo setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public String getDataType() {
    return dataType;
  }

  public FormFieldVo setDataType(String dataType) {
    this.dataType = dataType;
    return this;
  }

  public FieldUsage getUsage() {
    return usage;
  }

  public FormFieldVo setUsage(FieldUsage usage) {
    this.usage = usage;
    return this;
  }

}
