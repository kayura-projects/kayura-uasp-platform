/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.basic.entity;

import org.kayura.mybatis.annotation.mapper.*;
import org.kayura.uasp.dev.entity.ApplicEntity;
import org.kayura.uasp.dict.DataTypes;
import org.kayura.uasp.dict.DictFieldList;
import org.kayura.uasp.dict.DictScopes;
import org.kayura.uasp.dict.DictTypes;

/**
 * 数据词典定义(uasp_dict_define) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_dict_define")
public class DictDefineEntity {

  /** 词典分类ID */
  @Id
  private String defineId;
  /** 上级ID */
  @ForeignKey(entity = DictDefineEntity.class, alias = "pt")
  private String parentId;
  @RefColumn(from = "pt", value = "name_")
  private String parentName;
  /** 所属应用ID */
  @ForeignKey(entity = ApplicEntity.class, alias = "ap")
  private String appId;
  @RefColumn(from = "ap", value = "code_")
  private String appCode;
  @RefColumn(from = "ap", value = "name_")
  private String appName;
  /** 编号 */
  private String code;
  /** 显示名 */
  private String name;
  /** 应用范围:D开发;B业务; */
  private DictScopes scope;
  /** 定义类型：C字典分类,I字典项; */
  private DictTypes type;
  /** 数据类型:L列表;T树型; */
  private DataTypes dataType;
  /** 排序码 */
  @Sort
  private Integer sort;
  /** 扩展字段 */
  private DictFieldList extFields;
  /** 备注 */
  private String remark;

  public static DictDefineEntity create() {
    return new DictDefineEntity();
  }

  public String getDefineId() {
    return defineId;
  }

  public DictDefineEntity setDefineId(String defineId) {
    this.defineId = defineId;
    return this;
  }

  public String getParentId() {
    return parentId;
  }

  public DictDefineEntity setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public DictDefineEntity setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getAppCode() {
    return appCode;
  }

  public DictDefineEntity setAppCode(String appCode) {
    this.appCode = appCode;
    return this;
  }

  public String getName() {
    return name;
  }

  public DictDefineEntity setName(String name) {
    this.name = name;
    return this;
  }

  public DictScopes getScope() {
    return scope;
  }

  public DictDefineEntity setScope(DictScopes scope) {
    this.scope = scope;
    return this;
  }

  public DictTypes getType() {
    return type;
  }

  public DictDefineEntity setType(DictTypes type) {
    this.type = type;
    return this;
  }

  public DataTypes getDataType() {
    return dataType;
  }

  public DictDefineEntity setDataType(DataTypes dataType) {
    this.dataType = dataType;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public DictDefineEntity setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public DictFieldList getExtFields() {
    return extFields;
  }

  public DictDefineEntity setExtFields(DictFieldList extFields) {
    this.extFields = extFields;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public DictDefineEntity setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public String getAppName() {
    return appName;
  }

  public DictDefineEntity setAppName(String appName) {
    this.appName = appName;
    return this;
  }

  public String getCode() {
    return code;
  }

  public DictDefineEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getParentName() {
    return parentName;
  }

  public DictDefineEntity setParentName(String parentName) {
    this.parentName = parentName;
    return this;
  }
}