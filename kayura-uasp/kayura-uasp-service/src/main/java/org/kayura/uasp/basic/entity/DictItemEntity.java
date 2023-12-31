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

package org.kayura.uasp.basic.entity;

import org.kayura.data.Entity;
import org.kayura.mybatis.annotation.mapper.*;
import org.kayura.type.UsableStatus;

/**
 * 数据词典项(uasp_dict_item) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_dict_item")
public class DictItemEntity implements Entity {

  /** 词典项ID */
  @Id
  private String itemId;
  /** 词典分类ID */
  @ForeignKey(entity = DictDefineEntity.class, alias = "dd")
  private String defineId;
  @RefColumn(from = "dd", value = "code_")
  private String defineCode;
  @RefColumn(from = "dd", value = "name_")
  private String defineName;
  /** 上级ID */
  @ForeignKey(entity = DictItemEntity.class, alias = "pt")
  private String parentId;
  @RefColumn(from = "pt", value = "name_")
  private String parentName;
  /** 租户ID */
  private String tenantId;
  /** 编号 */
  private String code;
  /** 显示名 */
  private String name;
  /** 排序码 */
  @Sort
  private Integer sort;
  /** 状态:V可用,I禁用; */
  private UsableStatus status;
  /** 扩展字段1 */
  private String extField1;
  /** 扩展字段2 */
  private String extField2;
  /** 扩展字段3 */
  private String extField3;
  /** 扩展字段4 */
  private String extField4;
  /** 扩展字段5 */
  private String extField5;

  public static DictItemEntity create() {
    return new DictItemEntity();
  }

  public String getItemId() {
    return itemId;
  }

  public DictItemEntity setItemId(String itemId) {
    this.itemId = itemId;
    return this;
  }

  public String getDefineId() {
    return defineId;
  }

  public DictItemEntity setDefineId(String defineId) {
    this.defineId = defineId;
    return this;
  }

  public String getParentId() {
    return parentId;
  }

  public DictItemEntity setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public DictItemEntity setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public DictItemEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public DictItemEntity setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public DictItemEntity setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public UsableStatus getStatus() {
    return status;
  }

  public DictItemEntity setStatus(UsableStatus status) {
    this.status = status;
    return this;
  }

  public String getExtField1() {
    return extField1;
  }

  public DictItemEntity setExtField1(String extField1) {
    this.extField1 = extField1;
    return this;
  }

  public String getExtField2() {
    return extField2;
  }

  public DictItemEntity setExtField2(String extField2) {
    this.extField2 = extField2;
    return this;
  }

  public String getExtField3() {
    return extField3;
  }

  public DictItemEntity setExtField3(String extField3) {
    this.extField3 = extField3;
    return this;
  }

  public String getExtField4() {
    return extField4;
  }

  public DictItemEntity setExtField4(String extField4) {
    this.extField4 = extField4;
    return this;
  }

  public String getExtField5() {
    return extField5;
  }

  public DictItemEntity setExtField5(String extField5) {
    this.extField5 = extField5;
    return this;
  }

  public String getDefineName() {
    return defineName;
  }

  public DictItemEntity setDefineName(String defineName) {
    this.defineName = defineName;
    return this;
  }

  public String getParentName() {
    return parentName;
  }

  public DictItemEntity setParentName(String parentName) {
    this.parentName = parentName;
    return this;
  }

  public String getDefineCode() {
    return defineCode;
  }

  public DictItemEntity setDefineCode(String defineCode) {
    this.defineCode = defineCode;
    return this;
  }
}