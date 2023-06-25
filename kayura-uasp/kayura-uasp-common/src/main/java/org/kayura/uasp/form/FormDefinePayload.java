/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

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
