package org.kayura.uasp.organize;

public class LeaderVo {

  private String leaderId;
  private String leaderName;
  private String duty;

  public static LeaderVo create() {
    return new LeaderVo();
  }

  public String getLeaderId() {
    return leaderId;
  }

  public LeaderVo setLeaderId(String leaderId) {
    this.leaderId = leaderId;
    return this;
  }

  public String getLeaderName() {
    return leaderName;
  }

  public LeaderVo setLeaderName(String leaderName) {
    this.leaderName = leaderName;
    return this;
  }

  public String getDuty() {
    return duty;
  }

  public LeaderVo setDuty(String duty) {
    this.duty = duty;
    return this;
  }
}
