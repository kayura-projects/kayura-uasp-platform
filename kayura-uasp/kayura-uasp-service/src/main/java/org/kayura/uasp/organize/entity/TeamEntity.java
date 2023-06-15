package org.kayura.uasp.organize.entity;

import org.kayura.mybatis.annotation.mapper.*;
import org.kayura.type.DataStatus;
import org.kayura.uasp.auth.entity.UserEntity;

import java.time.LocalDateTime;

/**
 * 团队(uasp_team) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_team")
public class TeamEntity {

  /** 群组ID */
  @Id
  private String teamId;
  /** 租户ID */
  private String tenantId;
  /** 编号 */
  private String code;
  /** 名称 */
  private String name;
  /** 排序码 */
  @Sort
  private Integer sort;
  /** 成立时间 */
  private LocalDateTime startTime;
  /** 解散时间 */
  private LocalDateTime endTime;
  /** 创建人ID */
  private String creatorId;
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

  public static TeamEntity create() {
    return new TeamEntity();
  }

  public String getTeamId() {
    return teamId;
  }

  public TeamEntity setTeamId(String teamId) {
    this.teamId = teamId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public TeamEntity setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public TeamEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public TeamEntity setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public TeamEntity setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public TeamEntity setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
    return this;
  }

  public LocalDateTime getEndTime() {
    return endTime;
  }

  public TeamEntity setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public TeamEntity setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public TeamEntity setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public TeamEntity setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public TeamEntity setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public TeamEntity setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public TeamEntity setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public String getUpdaterName() {
    return updaterName;
  }

  public TeamEntity setUpdaterName(String updaterName) {
    this.updaterName = updaterName;
    return this;
  }
}