package org.kayura.uasp.form;

import org.kayura.type.DataStatus;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class FormDefinePayload {

  @NotBlank(groups = Update.class)
  private String formId;
  @NotBlank
  private String appId;
  @NotBlank
  private String code;
  @NotBlank
  private String name;
  @NotBlank
  private String category;
  @NotNull
  private Integer sort;
  private String icon;
  private String table;
  private String moduleId;
  @NotNull
  private FormTypes type;
  @NotNull
  private DataStatus status;
  private String remark;
  private List<FormFieldFrm> fields;

  public static FormDefinePayload create() {
    return new FormDefinePayload();
  }

  public String getFormId() {
    return formId;
  }

  public FormDefinePayload setFormId(String formId) {
    this.formId = formId;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public FormDefinePayload setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public FormDefinePayload setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public FormDefinePayload setName(String name) {
    this.name = name;
    return this;
  }

  public String getCategory() {
    return category;
  }

  public FormDefinePayload setCategory(String category) {
    this.category = category;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public FormDefinePayload setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public String getIcon() {
    return icon;
  }

  public FormDefinePayload setIcon(String icon) {
    this.icon = icon;
    return this;
  }

  public String getTable() {
    return table;
  }

  public FormDefinePayload setTable(String table) {
    this.table = table;
    return this;
  }

  public String getModuleId() {
    return moduleId;
  }

  public FormDefinePayload setModuleId(String moduleId) {
    this.moduleId = moduleId;
    return this;
  }

  public FormTypes getType() {
    return type;
  }

  public FormDefinePayload setType(FormTypes type) {
    this.type = type;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public FormDefinePayload setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public FormDefinePayload setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public List<FormFieldFrm> getFields() {
    return fields;
  }

  public FormDefinePayload setFields(List<FormFieldFrm> fields) {
    this.fields = fields;
    return this;
  }
}
