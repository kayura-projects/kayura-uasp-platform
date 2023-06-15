package org.kayura.uasp.role;

import org.kayura.type.DataStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RoleVo {

  public static final List<RoleVo> EMPTY = new ArrayList<>();

  private String roleId;
  private String appId;
  private String appName;
  private String tenantId;
  private String tenantName;
  private String code;
  private String name;
  private RoleTypes type;
  private Integer sort;
  private String remark;
  private String creatorId;
  private String creatorName;
  private LocalDateTime createTime;
  private String updaterId;
  private String updaterName;
  private LocalDateTime updateTime;
  private DataStatus status;

  public String getRoleId() {
    return roleId;
  }

  public RoleVo setRoleId(String roleId) {
    this.roleId = roleId;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public RoleVo setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getAppName() {
    return appName;
  }

  public RoleVo setAppName(String appName) {
    this.appName = appName;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public RoleVo setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public RoleVo setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public RoleVo setName(String name) {
    this.name = name;
    return this;
  }

  public RoleTypes getType() {
    return type;
  }

  public RoleVo setType(RoleTypes type) {
    this.type = type;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public RoleVo setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public RoleVo setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public RoleVo setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public String getCreatorName() {
    return creatorName;
  }

  public RoleVo setCreatorName(String creatorName) {
    this.creatorName = creatorName;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public RoleVo setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public RoleVo setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public String getUpdaterName() {
    return updaterName;
  }

  public RoleVo setUpdaterName(String updaterName) {
    this.updaterName = updaterName;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public RoleVo setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public RoleVo setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getTenantName() {
    return tenantName;
  }

  public RoleVo setTenantName(String tenantName) {
    this.tenantName = tenantName;
    return this;
  }
}
