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

import java.util.List;

public class FormDefineVo {

  private String formId;
  private String appId;
  private String appName;
  private String code;
  private String name;
  private String category;
  private Integer sort;
  private String icon;
  private String table;
  private String module;
  private FormTypes type;
  private DataStatus status;
  private String remark;
  private List<FormFieldVo> fields;

  public String getFormId() {
    return formId;
  }

  public FormDefineVo setFormId(String formId) {
    this.formId = formId;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public FormDefineVo setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public FormDefineVo setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public FormDefineVo setName(String name) {
    this.name = name;
    return this;
  }

  public String getCategory() {
    return category;
  }

  public FormDefineVo setCategory(String category) {
    this.category = category;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public FormDefineVo setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public String getIcon() {
    return icon;
  }

  public FormDefineVo setIcon(String icon) {
    this.icon = icon;
    return this;
  }

  public String getTable() {
    return table;
  }

  public FormDefineVo setTable(String table) {
    this.table = table;
    return this;
  }

  public String getModule() {
    return module;
  }

  public FormDefineVo setModule(String module) {
    this.module = module;
    return this;
  }

  public FormTypes getType() {
    return type;
  }

  public FormDefineVo setType(FormTypes type) {
    this.type = type;
    return this;
  }

  public String getTypeName() {
    return type != null ? type.getName() : null;
  }

  public DataStatus getStatus() {
    return status;
  }

  public FormDefineVo setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getStatusName() {
    return status != null ? status.getName() : null;
  }

  public String getRemark() {
    return remark;
  }

  public FormDefineVo setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public List<FormFieldVo> getFields() {
    return fields;
  }

  public FormDefineVo setFields(List<FormFieldVo> fields) {
    this.fields = fields;
    return this;
  }

  public String getAppName() {
    return appName;
  }

  public FormDefineVo setAppName(String appName) {
    this.appName = appName;
    return this;
  }
}
