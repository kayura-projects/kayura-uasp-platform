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

import org.kayura.type.UsableStatus;

import java.util.List;

public class DictItemVo {

  private String itemId;
  private String defineId;
  private String defineName;
  private String parentId;
  private String parentName;
  private String tenantId;
  private String code;
  private String name;
  private Integer sort;
  private UsableStatus status;
  private List<DictItemVo> children;
  private String extField1;
  private String extField2;
  private String extField3;
  private String extField4;
  private String extField5;

  public String getItemId() {
    return itemId;
  }

  public DictItemVo setItemId(String itemId) {
    this.itemId = itemId;
    return this;
  }

  public String getDefineId() {
    return defineId;
  }

  public DictItemVo setDefineId(String defineId) {
    this.defineId = defineId;
    return this;
  }

  public String getDefineName() {
    return defineName;
  }

  public DictItemVo setDefineName(String defineName) {
    this.defineName = defineName;
    return this;
  }

  public String getParentId() {
    return parentId;
  }

  public DictItemVo setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public String getParentName() {
    return parentName;
  }

  public DictItemVo setParentName(String parentName) {
    this.parentName = parentName;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public DictItemVo setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public DictItemVo setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public DictItemVo setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public DictItemVo setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public UsableStatus getStatus() {
    return status;
  }

  public DictItemVo setStatus(UsableStatus status) {
    this.status = status;
    return this;
  }

  public String getExtField1() {
    return extField1;
  }

  public DictItemVo setExtField1(String extField1) {
    this.extField1 = extField1;
    return this;
  }

  public String getExtField2() {
    return extField2;
  }

  public DictItemVo setExtField2(String extField2) {
    this.extField2 = extField2;
    return this;
  }

  public String getExtField3() {
    return extField3;
  }

  public DictItemVo setExtField3(String extField3) {
    this.extField3 = extField3;
    return this;
  }

  public String getExtField4() {
    return extField4;
  }

  public DictItemVo setExtField4(String extField4) {
    this.extField4 = extField4;
    return this;
  }

  public String getExtField5() {
    return extField5;
  }

  public DictItemVo setExtField5(String extField5) {
    this.extField5 = extField5;
    return this;
  }

  public List<DictItemVo> getChildren() {
    return children;
  }

  public DictItemVo setChildren(List<DictItemVo> children) {
    this.children = children;
    return this;
  }
}
