package org.kayura.uasp.organize;

import org.kayura.mybatis.annotation.querier.Like;

import javax.validation.constraints.NotBlank;

public class IdentityQuery {

  @NotBlank
  private String employeeId;
  private JobStatus status;
  @Like("departName,positionName")
  private String searchText;

  public static IdentityQuery create() {
    return new IdentityQuery();
  }

  public String getEmployeeId() {
    return employeeId;
  }

  public IdentityQuery setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public IdentityQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }

  public JobStatus getStatus() {
    return status;
  }

  public IdentityQuery setStatus(JobStatus status) {
    this.status = status;
    return this;
  }
}
