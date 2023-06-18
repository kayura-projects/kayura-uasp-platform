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
