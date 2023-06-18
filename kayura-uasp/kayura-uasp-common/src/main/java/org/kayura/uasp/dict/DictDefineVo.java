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
