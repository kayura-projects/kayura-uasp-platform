/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
 -----------------------------------------------------------------------------*/

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
