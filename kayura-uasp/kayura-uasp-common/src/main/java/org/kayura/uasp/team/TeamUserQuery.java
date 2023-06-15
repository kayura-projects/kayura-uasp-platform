package org.kayura.uasp.team;

import org.kayura.mybatis.annotation.querier.Like;

public class TeamUserQuery {

  private String teamId;
  private String duty;
  @Like("userName")
  private String searchText;

  public String getDuty() {
    return duty;
  }

  public TeamUserQuery setDuty(String duty) {
    this.duty = duty;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public TeamUserQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }

  public String getTeamId() {
    return teamId;
  }

  public TeamUserQuery setTeamId(String teamId) {
    this.teamId = teamId;
    return this;
  }
}
