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

package org.kayura.uasp.dict;

public class DictDefineVo {

  private String defineId;
  private String parentId;
  private String parentName;
  private String appId;
  private String appCode;
  private String appName;
  private String code;
  private String name;
  private DictScopes scope;
  private DictTypes type;
  private DataTypes dataType;
  private Integer sort;
  private DictFieldList extFields;
  private String remark;

  public static DictDefineVo create() {
    return new DictDefineVo();
  }

  public String getDefineId() {
    return defineId;
  }

  public DictDefineVo setDefineId(String defineId) {
    this.defineId = defineId;
    return this;
  }

  public String getParentId() {
    return parentId;
  }

  public DictDefineVo setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public String getParentName() {
    return parentName;
  }

  public DictDefineVo setParentName(String parentName) {
    this.parentName = parentName;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public DictDefineVo setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getAppCode() {
    return appCode;
  }

  public DictDefineVo setAppCode(String appCode) {
    this.appCode = appCode;
    return this;
  }

  public String getAppName() {
    return appName;
  }

  public DictDefineVo setAppName(String appName) {
    this.appName = appName;
    return this;
  }

  public String getCode() {
    return code;
  }

  public DictDefineVo setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public DictDefineVo setName(String name) {
    this.name = name;
    return this;
  }

  public DictScopes getScope() {
    return scope;
  }

  public DictDefineVo setScope(DictScopes scope) {
    this.scope = scope;
    return this;
  }

  public DictTypes getType() {
    return type;
  }

  public DictDefineVo setType(DictTypes type) {
    this.type = type;
    return this;
  }

  public DataTypes getDataType() {
    return dataType;
  }

  public DictDefineVo setDataType(DataTypes dataType) {
    this.dataType = dataType;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public DictDefineVo setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public DictFieldList getExtFields() {
    return extFields;
  }

  public DictDefineVo setExtFields(DictFieldList extFields) {
    this.extFields = extFields;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public DictDefineVo setRemark(String remark) {
    this.remark = remark;
    return this;
  }
}
