/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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