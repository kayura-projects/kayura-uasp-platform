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