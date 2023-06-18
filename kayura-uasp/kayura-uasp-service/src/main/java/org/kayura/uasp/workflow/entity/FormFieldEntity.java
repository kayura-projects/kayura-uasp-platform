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

package org.kayura.uasp.workflow.entity;

import org.kayura.mybatis.annotation.mapper.*;
import org.kayura.uasp.form.FieldUsage;

/**
 * 表单字段(uasp_form_field) 实体定义
 *
 * @author liangxia@live.com
 */
@Table("uasp_form_field")
public class FormFieldEntity {

  /** 表单字段ID */
  @Id
  private String fieldId;
  /** 业务表单ID */
  private String formId;
  /** 字段名 */
  private String fieldName;
  /** 属性名 */
  private String propName;
  /** 显示名 */
  private String displayName;
  /** 排序号 */
  @Sort
  private Integer sort;
  /** 数据类型 */
  private String dataType;
  /** 字段类型:M主键,F流转码,S状态码 */
  private FieldUsage usage;

  public static FormFieldEntity create() {
    return new FormFieldEntity();
  }

  public String getFormId() {
    return formId;
  }

  public FormFieldEntity setFormId(String formId) {
    this.formId = formId;
    return this;
  }

  public String getFieldName() {
    return fieldName;
  }

  public FormFieldEntity setFieldName(String fieldName) {
    this.fieldName = fieldName;
    return this;
  }

  public String getPropName() {
    return propName;
  }

  public FormFieldEntity setPropName(String propName) {
    this.propName = propName;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public FormFieldEntity setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public FormFieldEntity setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public String getDataType() {
    return dataType;
  }

  public FormFieldEntity setDataType(String dataType) {
    this.dataType = dataType;
    return this;
  }

  public FieldUsage getUsage() {
    return usage;
  }

  public FormFieldEntity setUsage(FieldUsage usage) {
    this.usage = usage;
    return this;
  }

  public String getFieldId() {
    return fieldId;
  }

  public FormFieldEntity setFieldId(String fieldId) {
    this.fieldId = fieldId;
    return this;
  }
}