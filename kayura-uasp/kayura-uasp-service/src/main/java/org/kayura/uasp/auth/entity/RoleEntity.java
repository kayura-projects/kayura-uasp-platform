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

package org.kayura.uasp.auth.entity;

import org.kayura.mybatis.annotation.mapper.*;
import org.kayura.type.UsableStatus;
import org.kayura.uasp.dev.entity.ApplicEntity;
import org.kayura.uasp.organize.entity.CompanyEntity;
import org.kayura.uasp.role.RoleTypes;

import java.time.LocalDateTime;

/**
 * 角色(uasp_role) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_role")
public class RoleEntity {

  /** 角色ID */
  @Id
  private String roleId;
  /** 应用ID */
  @ForeignKey(entity = ApplicEntity.class, alias = "app")
  private String appId;
  @RefColumn(from = "app", value = "name_")
  private String appName;
  /** 租户ID */
  @ForeignKey(entity = CompanyEntity.class, alias = "cc")
  private String tenantId;
  @RefColumn(from = "cc", value = "short_name_")
  private String tenantName;
  /** 编号 */
  private String code;
  /** 名称 */
  private String name;
  /** 类型:FN功能角色;WF流程角色;DT数据角色; */
  private RoleTypes type;
  /** 排序码 */
  @Sort
  private Integer sort;
  /** 备注 */
  private String remark;
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
  /** 状态: V可用,I禁用; */
  private UsableStatus status;

  public static RoleEntity create() {
    return new RoleEntity();
  }

  public String getCreatorName() {
    return creatorName;
  }

  public RoleEntity setCreatorName(String creatorName) {
    this.creatorName = creatorName;
    return this;
  }

  public String getUpdaterName() {
    return updaterName;
  }

  public RoleEntity setUpdaterName(String updaterName) {
    this.updaterName = updaterName;
    return this;
  }

  public String getRoleId() {
    return roleId;
  }

  public RoleEntity setRoleId(String roleId) {
    this.roleId = roleId;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public RoleEntity setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public RoleEntity setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public RoleEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public RoleEntity setName(String name) {
    this.name = name;
    return this;
  }

  public RoleTypes getType() {
    return type;
  }

  public RoleEntity setType(RoleTypes type) {
    this.type = type;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public RoleEntity setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public RoleEntity setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public RoleEntity setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public RoleEntity setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public RoleEntity setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public RoleEntity setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public UsableStatus getStatus() {
    return status;
  }

  public RoleEntity setStatus(UsableStatus status) {
    this.status = status;
    return this;
  }

  public String getAppName() {
    return appName;
  }

  public RoleEntity setAppName(String appName) {
    this.appName = appName;
    return this;
  }

  public String getTenantName() {
    return tenantName;
  }

  public RoleEntity setTenantName(String tenantName) {
    this.tenantName = tenantName;
    return this;
  }
}