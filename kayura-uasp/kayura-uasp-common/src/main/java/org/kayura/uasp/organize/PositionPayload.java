package org.kayura.uasp.organize;

import org.kayura.type.DataStatus;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PositionPayload {

  @NotBlank(groups = Update.class)
  private String positionId;
  @NotBlank(groups = Create.class)
  private String departId;
  private String code;
  @NotBlank(groups = Create.class)
  private String name;
  @NotNull(groups = Update.class)
  private Integer level;
  @NotNull(groups = Update.class)
  private Integer sort;
  @NotNull(groups = Update.class)
  private DataStatus status;
  private String remark;

  public String getDepartId() {
    return departId;
  }

  public PositionPayload setDepartId(String departId) {
    this.departId = departId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public PositionPayload setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public PositionPayload setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public PositionPayload setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public PositionPayload setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public PositionPayload setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public String getPositionId() {
    return positionId;
  }

  public PositionPayload setPositionId(String positionId) {
    this.positionId = positionId;
    return this;
  }

  public Integer getLevel() {
    return level;
  }

  public PositionPayload setLevel(Integer level) {
    this.level = level;
    return this;
  }
}
