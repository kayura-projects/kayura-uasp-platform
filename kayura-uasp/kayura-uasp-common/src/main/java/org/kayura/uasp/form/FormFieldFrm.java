package org.kayura.uasp.form;

public class FormFieldFrm {

  private String formId;
  private String fieldName;
  private String propName;
  private String displayName;
  private Integer sort;
  private String dataType;
  private FieldUsage usage;

  public static FormFieldFrm create() {
    return new FormFieldFrm();
  }

  public String getFormId() {
    return formId;
  }

  public FormFieldFrm setFormId(String formId) {
    this.formId = formId;
    return this;
  }

  public String getFieldName() {
    return fieldName;
  }

  public FormFieldFrm setFieldName(String fieldName) {
    this.fieldName = fieldName;
    return this;
  }

  public String getPropName() {
    return propName;
  }

  public FormFieldFrm setPropName(String propName) {
    this.propName = propName;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public FormFieldFrm setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public FormFieldFrm setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public String getDataType() {
    return dataType;
  }

  public FormFieldFrm setDataType(String dataType) {
    this.dataType = dataType;
    return this;
  }

  public FieldUsage getUsage() {
    return usage;
  }

  public FormFieldFrm setUsage(FieldUsage usage) {
    this.usage = usage;
    return this;
  }
}
