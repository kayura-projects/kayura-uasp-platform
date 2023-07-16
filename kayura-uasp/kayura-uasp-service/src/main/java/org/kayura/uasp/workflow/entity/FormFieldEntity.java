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

package org.kayura.uasp.workflow.entity;

import org.kayura.data.Entity;
import org.kayura.mybatis.annotation.mapper.*;
import org.kayura.uasp.form.FieldUsage;

/**
 * 表单字段(uasp_form_field) 实体定义
 *
 * @author liangxia@live.com
 */
@Table("uasp_form_field")
public class FormFieldEntity implements Entity {

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