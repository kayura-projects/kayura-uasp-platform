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