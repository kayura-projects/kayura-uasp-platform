/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
