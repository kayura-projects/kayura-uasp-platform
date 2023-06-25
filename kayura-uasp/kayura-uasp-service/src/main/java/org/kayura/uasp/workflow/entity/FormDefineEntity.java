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

import org.kayura.mybatis.annotation.mapper.*;
import org.kayura.type.DataStatus;
import org.kayura.uasp.dev.entity.ApplicEntity;
import org.kayura.uasp.form.FormTypes;

/**
 * 表单定义(uasp_form_define) 实体定义
 *
 * @author liangxia@live.com
 */
@Table("uasp_form_define")
public class FormDefineEntity {

  /** 业务表单ID */
  @Id
  private String formId;
  /** 租户ID */
  @ForeignKey(entity = ApplicEntity.class, alias = "app")
  private String appId;
  @RefColumn(from = "app", value = "name_")
  private String appName;
  /** 表单编号 */
  private String code;
  /** 显示名 */
  private String name;
  /** 表单分组 */
  private String category;
  /** 排序码 */
  private Integer sort;
  /** 图标 */
  private String icon;
  /** 物理表名 */
  private String table;
  /** 绑定模块 */
  private String moduleId;
  /** 表单类型:B 业务表单；C 自定义表单； */
  private FormTypes type;
  /** 状态:D草搞,V可用,I禁用; */
  private DataStatus status;
  /** 备注 */
  private String remark;

  public static FormDefineEntity create() {
    return new FormDefineEntity();
  }

  public String getFormId() {
    return formId;
  }

  public FormDefineEntity setFormId(String formId) {
    this.formId = formId;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public FormDefineEntity setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public FormDefineEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public FormDefineEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getCategory() {
    return category;
  }

  public FormDefineEntity setCategory(String category) {
    this.category = category;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public FormDefineEntity setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public String getIcon() {
    return icon;
  }

  public FormDefineEntity setIcon(String icon) {
    this.icon = icon;
    return this;
  }

  public String getTable() {
    return table;
  }

  public FormDefineEntity setTable(String table) {
    this.table = table;
    return this;
  }

  public String getModuleId() {
    return moduleId;
  }

  public FormDefineEntity setModuleId(String moduleId) {
    this.moduleId = moduleId;
    return this;
  }

  public FormTypes getType() {
    return type;
  }

  public FormDefineEntity setType(FormTypes type) {
    this.type = type;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public FormDefineEntity setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public FormDefineEntity setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public String getAppName() {
    return appName;
  }

  public FormDefineEntity setAppName(String appName) {
    this.appName = appName;
    return this;
  }
}