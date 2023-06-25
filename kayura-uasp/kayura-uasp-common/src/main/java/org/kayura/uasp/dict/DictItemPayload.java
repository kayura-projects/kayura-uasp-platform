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
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DictItemPayload {

  @NotBlank(groups = Update.class)
  private String itemId;
  @NotBlank(groups = Create.class)
  private String defineId;
  private String parentId;
  private String tenantId;
  private String code;
  @NotBlank
  private String name;
  @NotNull
  private Integer sort;
  @NotNull
  private UsableStatus status;
  private String extField1;
  private String extField2;
  private String extField3;
  private String extField4;
  private String extField5;

  public String getItemId() {
    return itemId;
  }

  public DictItemPayload setItemId(String itemId) {
    this.itemId = itemId;
    return this;
  }

  public String getDefineId() {
    return defineId;
  }

  public DictItemPayload setDefineId(String defineId) {
    this.defineId = defineId;
    return this;
  }

  public String getParentId() {
    return parentId;
  }

  public DictItemPayload setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public DictItemPayload setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public DictItemPayload setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public DictItemPayload setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public DictItemPayload setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public UsableStatus getStatus() {
    return status;
  }

  public DictItemPayload setStatus(UsableStatus status) {
    this.status = status;
    return this;
  }

  public String getExtField1() {
    return extField1;
  }

  public DictItemPayload setExtField1(String extField1) {
    this.extField1 = extField1;
    return this;
  }

  public String getExtField2() {
    return extField2;
  }

  public DictItemPayload setExtField2(String extField2) {
    this.extField2 = extField2;
    return this;
  }

  public String getExtField3() {
    return extField3;
  }

  public DictItemPayload setExtField3(String extField3) {
    this.extField3 = extField3;
    return this;
  }

  public String getExtField4() {
    return extField4;
  }

  public DictItemPayload setExtField4(String extField4) {
    this.extField4 = extField4;
    return this;
  }

  public String getExtField5() {
    return extField5;
  }

  public DictItemPayload setExtField5(String extField5) {
    this.extField5 = extField5;
    return this;
  }
}
