package org.kayura.uasp.applic;

import org.kayura.mybatis.annotation.querier.Like;
import org.kayura.type.DataStatus;

public class ApplicQuery {

  private Integer level;
  private DataStatus status;
  @Like("code,name")
  private String searchText;

  public static ApplicQuery create() {
    return new ApplicQuery();
  }

  public Integer getLevel() {
    return level;
  }

  public ApplicQuery setLevel(Integer level) {
    this.level = level;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public ApplicQuery setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public ApplicQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }
}
