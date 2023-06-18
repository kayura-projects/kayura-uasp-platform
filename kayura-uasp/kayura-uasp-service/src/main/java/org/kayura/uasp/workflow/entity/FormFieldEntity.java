/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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