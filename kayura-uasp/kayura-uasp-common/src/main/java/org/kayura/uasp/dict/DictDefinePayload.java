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

package org.kayura.uasp.dict;

import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DictDefinePayload {

  @NotBlank(groups = Update.class)
  private String defineId;
  private String parentId;
  @NotBlank(groups = Create.class)
  private String appId;
  private String code;
  @NotBlank
  private String name;
  private DictScopes scope;
  @NotNull(groups = Create.class)
  private DictTypes type;
  private DataTypes dataType;
  @NotNull
  private Integer sort;
  private DictFieldList extFields;
  private String remark;

  public String getParentId() {
    return parentId;
  }

  public DictDefinePayload setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public DictDefinePayload setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getName() {
    return name;
  }

  public DictDefinePayload setName(String name) {
    this.name = name;
    return this;
  }

  public DictScopes getScope() {
    return scope;
  }

  public DictDefinePayload setScope(DictScopes scope) {
    this.scope = scope;
    return this;
  }

  public DictTypes getType() {
    return type;
  }

  public DictDefinePayload setType(DictTypes type) {
    this.type = type;
    return this;
  }

  public DataTypes getDataType() {
    return dataType;
  }

  public DictDefinePayload setDataType(DataTypes dataType) {
    this.dataType = dataType;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public DictDefinePayload setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public DictFieldList getExtFields() {
    return extFields;
  }

  public DictDefinePayload setExtFields(DictFieldList extFields) {
    this.extFields = extFields;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public DictDefinePayload setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public String getDefineId() {
    return defineId;
  }

  public DictDefinePayload setDefineId(String defineId) {
    this.defineId = defineId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public DictDefinePayload setCode(String code) {
    this.code = code;
    return this;
  }
}
