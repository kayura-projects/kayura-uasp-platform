package org.kayura.uasp.organize;

import org.kayura.type.DataStatus;

import java.time.LocalDateTime;

public class PositionVo {

  private String positionId;
  private String departId;
  private String departName;
  private String code;
  private String name;
  private Integer sort;
  private Integer level;
  private String creatorId;
  private String creatorName;
  private LocalDateTime createTime;
  private String updaterId;
  private String updaterName;
  private LocalDateTime updateTime;
  private DataStatus status;
  private String remark;

  public String getPositionId() {
    return positionId;
  }

  public PositionVo setPositionId(String positionId) {
    this.positionId = positionId;
    return this;
  }

  public String getDepartId() {
    return departId;
  }

  public PositionVo setDepartId(String departId) {
    this.departId = departId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public PositionVo setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public PositionVo setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public PositionVo setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public Integer getLevel() {
    return level;
  }

  public PositionVo setLevel(Integer level) {
    this.level = level;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public PositionVo setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public PositionVo setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public PositionVo setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public PositionVo setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public PositionVo setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public PositionVo setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public String getCreatorName() {
    return creatorName;
  }

  public void setCreatorName(String creatorName) {
    this.creatorName = creatorName;
  }

  public String getUpdaterName() {
    return updaterName;
  }

  public void setUpdaterName(String updaterName) {
    this.updaterName = updaterName;
  }

  public String getDepartName() {
    return departName;
  }

  public PositionVo setDepartName(String departName) {
    this.departName = departName;
    return this;
  }
}
