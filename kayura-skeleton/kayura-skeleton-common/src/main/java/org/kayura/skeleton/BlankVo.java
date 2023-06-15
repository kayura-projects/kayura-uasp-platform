package org.kayura.skeleton;

import org.kayura.type.DataStatus;

import java.time.LocalDateTime;

public class BlankVo {

  private String id;
  private String code;
  private String name;
  private String creatorId;
  private LocalDateTime createTime;
  private String updaterId;
  private LocalDateTime updateTime;
  private DataStatus status;

  public String getId() {
    return id;
  }

  public BlankVo setId(String id) {
    this.id = id;
    return this;
  }

  public String getCode() {
    return code;
  }

  public BlankVo setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public BlankVo setName(String name) {
    this.name = name;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public BlankVo setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public BlankVo setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public BlankVo setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public BlankVo setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public BlankVo setStatus(DataStatus status) {
    this.status = status;
    return this;
  }
}
