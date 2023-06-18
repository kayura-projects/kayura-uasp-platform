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

package org.kayura.uasp.organize.entity;

import org.kayura.mybatis.annotation.mapper.*;
import org.kayura.type.DataStatus;

import java.time.LocalDateTime;

/**
 * 团队成员(uasp_team_user) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_team_user")
public class TeamUserEntity {

  /** 小组成员ID */
  @Id
  private String memberId;
  /** 群组ID */
  @ForeignKey(entity = TeamEntity.class, alias = "tt")
  private String teamId;
  @RefColumn(from = "tt", value = "name_")
  private String teamName;
  @RefColumn(from = "tt", value = "status_")
  private DataStatus teamStatus;
  /** 成员Id */
  @ForeignKey(entity = EmployeeEntity.class, alias = "emp")
  private String employeeId;
  @RefColumn(from = "emp", value = "real_name_")
  private String employeeName;
  /** 职责 */
  private String duty;
  /** 级别:0-N 等级由高至低 */
  private Integer level;
  /** 加入时间 */
  private LocalDateTime joinTime;
  /** 离开时间 */
  private LocalDateTime leaveTime;
  /** 备注 */
  private String remark;

  public static TeamUserEntity create() {
    return new TeamUserEntity();
  }

  public String getMemberId() {
    return memberId;
  }

  public TeamUserEntity setMemberId(String memberId) {
    this.memberId = memberId;
    return this;
  }

  public String getTeamId() {
    return teamId;
  }

  public TeamUserEntity setTeamId(String teamId) {
    this.teamId = teamId;
    return this;
  }

  public String getEmployeeId() {
    return employeeId;
  }

  public TeamUserEntity setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
    return this;
  }

  public String getDuty() {
    return duty;
  }

  public TeamUserEntity setDuty(String duty) {
    this.duty = duty;
    return this;
  }

  public Integer getLevel() {
    return level;
  }

  public TeamUserEntity setLevel(Integer level) {
    this.level = level;
    return this;
  }

  public LocalDateTime getJoinTime() {
    return joinTime;
  }

  public TeamUserEntity setJoinTime(LocalDateTime joinTime) {
    this.joinTime = joinTime;
    return this;
  }

  public LocalDateTime getLeaveTime() {
    return leaveTime;
  }

  public TeamUserEntity setLeaveTime(LocalDateTime leaveTime) {
    this.leaveTime = leaveTime;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public TeamUserEntity setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public DataStatus getTeamStatus() {
    return teamStatus;
  }

  public TeamUserEntity setTeamStatus(DataStatus teamStatus) {
    this.teamStatus = teamStatus;
    return this;
  }

  public String getTeamName() {
    return teamName;
  }

  public TeamUserEntity setTeamName(String teamName) {
    this.teamName = teamName;
    return this;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public TeamUserEntity setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
    return this;
  }
}