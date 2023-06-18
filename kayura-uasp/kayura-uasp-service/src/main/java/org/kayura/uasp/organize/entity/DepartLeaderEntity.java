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

package org.kayura.uasp.organize.entity;

import org.kayura.mybatis.annotation.mapper.*;

@Table("uasp_depart_leader")
public class DepartLeaderEntity {

  private String departId;
  @ForeignKey(entity = EmployeeEntity.class, alias = "ee")
  private String leaderId;
  @RefColumn(from = "ee", value = "real_name_")
  private String leaderName;
  private String duty;

  public static DepartLeaderEntity create() {
    return new DepartLeaderEntity();
  }

  public String getDepartId() {
    return departId;
  }

  public DepartLeaderEntity setDepartId(String departId) {
    this.departId = departId;
    return this;
  }

  public String getLeaderId() {
    return leaderId;
  }

  public DepartLeaderEntity setLeaderId(String leaderId) {
    this.leaderId = leaderId;
    return this;
  }

  public String getLeaderName() {
    return leaderName;
  }

  public DepartLeaderEntity setLeaderName(String leaderName) {
    this.leaderName = leaderName;
    return this;
  }

  public String getDuty() {
    return duty;
  }

  public DepartLeaderEntity setDuty(String duty) {
    this.duty = duty;
    return this;
  }
}