package org.kayura.uasp.team;

import org.kayura.vaildation.Create;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class TeamUserBody {

  @NotBlank(groups = Create.class)
  private String teamId;
  @NotBlank(groups = Create.class)
  private String employeeId;
  private String duty;
  private Integer level;
  private LocalDateTime joinTime;
  private LocalDateTime leaveTime;
  private String remark;

  public String getTeamId() {
    return teamId;
  }

  public TeamUserBody setTeamId(String teamId) {
    this.teamId = teamId;
    return this;
  }

  public String getEmployeeId() {
    return employeeId;
  }

  public TeamUserBody setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
    return this;
  }

  public String getDuty() {
    return duty;
  }

  public TeamUserBody setDuty(String duty) {
    this.duty = duty;
    return this;
  }

  public Integer getLevel() {
    return level;
  }

  public TeamUserBody setLevel(Integer level) {
    this.level = level;
    return this;
  }

  public LocalDateTime getJoinTime() {
    return joinTime;
  }

  public TeamUserBody setJoinTime(LocalDateTime joinTime) {
    this.joinTime = joinTime;
    return this;
  }

  public LocalDateTime getLeaveTime() {
    return leaveTime;
  }

  public TeamUserBody setLeaveTime(LocalDateTime leaveTime) {
    this.leaveTime = leaveTime;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public TeamUserBody setRemark(String remark) {
    this.remark = remark;
    return this;
  }
}
