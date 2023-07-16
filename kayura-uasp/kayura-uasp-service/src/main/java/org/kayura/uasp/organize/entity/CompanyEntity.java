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

package org.kayura.uasp.organize.entity;

import org.kayura.data.Entity;
import org.kayura.mybatis.annotation.mapper.*;
import org.kayura.type.DataStatus;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.basic.entity.DictItemEntity;
import org.kayura.uasp.company.CompanyTypes;

import java.time.LocalDateTime;

/**
 * 公司(uasp_company) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_company")
public class CompanyEntity implements Entity {

  /** 公司ID */
  @Id
  private String companyId;
  /** 上级ID */
  private String parentId;
  /** 所属租户ID */
  private String tenantId;
  /** 类型:TENANT,CONTACT */
  private CompanyTypes type;
  /** 分类ID */
  @ForeignKey(entity = DictItemEntity.class, alias = "cat", pkName = "item_id_")
  private String categoryId;
  @RefColumn(from = "cat", value = "name_")
  private String categoryName;
  /** 编号 */
  private String code;
  /** 公司简称 */
  private String shortName;
  /** 公司全称 */
  private String fullName;
  /** 办公地址 */
  private String address;
  /** 邮编 */
  private String postCode;
  /** 联系人 */
  private String contract;
  /** 联系手机 */
  private String mobile;
  /** 联系电话 */
  private String tel;
  /** 传真号 */
  private String fax;
  /** 联系邮箱 */
  private String email;
  /** 排序码 */
  @Sort
  private Integer sort;
  /** 经度 */
  private String location;
  /** 创建人ID */
  @ForeignKey(entity = UserEntity.class, alias = "cu")
  private String creatorId;
  @RefColumn(from = "cu", value = "display_name_")
  private String creatorName;
  /** 创建时间 */
  private LocalDateTime createTime;
  /** 修改人ID */
  @ForeignKey(entity = UserEntity.class, alias = "uu")
  private String updaterId;
  @RefColumn(from = "uu", value = "display_name_")
  private String updaterName;
  /** 修改时间 */
  private LocalDateTime updateTime;
  /** 状态:D草搞,V可用,I禁用; */
  private DataStatus status;
  /** 备注 */
  private String remark;

  public static CompanyEntity create() {
    return new CompanyEntity();
  }

  public String getCompanyId() {
    return companyId;
  }

  public CompanyEntity setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public String getParentId() {
    return parentId;
  }

  public CompanyEntity setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public CompanyEntity setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public CompanyTypes getType() {
    return type;
  }

  public CompanyEntity setType(CompanyTypes type) {
    this.type = type;
    return this;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public CompanyEntity setCategoryId(String categoryId) {
    this.categoryId = categoryId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public CompanyEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getShortName() {
    return shortName;
  }

  public CompanyEntity setShortName(String shortName) {
    this.shortName = shortName;
    return this;
  }

  public String getFullName() {
    return fullName;
  }

  public CompanyEntity setFullName(String fullName) {
    this.fullName = fullName;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public CompanyEntity setAddress(String address) {
    this.address = address;
    return this;
  }

  public String getPostCode() {
    return postCode;
  }

  public CompanyEntity setPostCode(String postCode) {
    this.postCode = postCode;
    return this;
  }

  public String getContract() {
    return contract;
  }

  public CompanyEntity setContract(String contract) {
    this.contract = contract;
    return this;
  }

  public String getTel() {
    return tel;
  }

  public CompanyEntity setTel(String tel) {
    this.tel = tel;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public CompanyEntity setEmail(String email) {
    this.email = email;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public CompanyEntity setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public String getLocation() {
    return location;
  }

  public CompanyEntity setLocation(String location) {
    this.location = location;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public CompanyEntity setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public CompanyEntity setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public CompanyEntity setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public CompanyEntity setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public CompanyEntity setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public CompanyEntity setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public CompanyEntity setCategoryName(String categoryName) {
    this.categoryName = categoryName;
    return this;
  }

  public String getCreatorName() {
    return creatorName;
  }

  public CompanyEntity setCreatorName(String creatorName) {
    this.creatorName = creatorName;
    return this;
  }

  public String getUpdaterName() {
    return updaterName;
  }

  public CompanyEntity setUpdaterName(String updaterName) {
    this.updaterName = updaterName;
    return this;
  }

  public String getMobile() {
    return mobile;
  }

  public CompanyEntity setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public String getFax() {
    return fax;
  }

  public CompanyEntity setFax(String fax) {
    this.fax = fax;
    return this;
  }
}