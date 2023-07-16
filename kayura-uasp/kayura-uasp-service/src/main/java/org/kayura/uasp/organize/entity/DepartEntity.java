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

import java.time.LocalDateTime;

/**
 * 部门(uasp_depart) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_depart")
public class DepartEntity implements Entity {

  /** 部门ID */
  @Id
  private String departId;
  /** 上级ID */
  @ForeignKey(entity = DepartEntity.class, alias = "pt")
  private String parentId;
  @RefColumn(from = "pt", value = "name_")
  private String parentName;
  /** 所属公司ID */
  @ForeignKey(entity = CompanyEntity.class, alias = "cc")
  private String companyId;
  @RefColumn(from = "cc", value = "short_name_")
  private String companyName;
  @RefColumn(from = "cc", value = "tenant_id_")
  private String tenantId;
  /** 编号 */
  private String code;
  /** 名称 */
  private String name;
  /** 排序码 */
  @Sort
  private Integer sort;
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

  public static DepartEntity create() {
    return new DepartEntity();
  }

  public String getDepartId() {
    return departId;
  }

  public DepartEntity setDepartId(String departId) {
    this.departId = departId;
    return this;
  }

  public String getParentId() {
    return parentId;
  }

  public DepartEntity setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public String getCompanyId() {
    return companyId;
  }

  public DepartEntity setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public DepartEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public DepartEntity setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public DepartEntity setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public DepartEntity setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public DepartEntity setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public DepartEntity setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public DepartEntity setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public DepartEntity setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public DepartEntity setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public DepartEntity setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getCreatorName() {
    return creatorName;
  }

  public DepartEntity setCreatorName(String creatorName) {
    this.creatorName = creatorName;
    return this;
  }

  public String getUpdaterName() {
    return updaterName;
  }

  public DepartEntity setUpdaterName(String updaterName) {
    this.updaterName = updaterName;
    return this;
  }

  public String getParentName() {
    return parentName;
  }

  public DepartEntity setParentName(String parentName) {
    this.parentName = parentName;
    return this;
  }

  public String getCompanyName() {
    return companyName;
  }

  public DepartEntity setCompanyName(String companyName) {
    this.companyName = companyName;
    return this;
  }
}