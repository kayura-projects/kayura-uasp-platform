/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.organize;

import org.kayura.mybatis.annotation.Ignore;
import org.kayura.mybatis.annotation.querier.In;
import org.kayura.mybatis.annotation.querier.Like;

import java.util.List;

public class EmployeeQuery {

  @Ignore
  private String orgId;
  @Ignore
  private OrganizeTypes orgType;
  private String tenantId;
  private JobStatus status;
  @Like("realName,userName,displayName,mobile")
  private String searchText;
  @In("employeeId")
  private List<String> employeeIds;
  @In("userName")
  private List<String> userNames;

  public JobStatus getStatus() {
    return status;
  }

  public EmployeeQuery setStatus(JobStatus status) {
    this.status = status;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public EmployeeQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }

  public String getOrgId() {
    return orgId;
  }

  public EmployeeQuery setOrgId(String orgId) {
    this.orgId = orgId;
    return this;
  }

  public OrganizeTypes getOrgType() {
    return orgType;
  }

  public EmployeeQuery setOrgType(OrganizeTypes orgType) {
    this.orgType = orgType;
    return this;
  }

  public List<String> getEmployeeIds() {
    return employeeIds;
  }

  public EmployeeQuery setEmployeeIds(List<String> employeeIds) {
    this.employeeIds = employeeIds;
    return this;
  }

  public List<String> getUserNames() {
    return userNames;
  }

  public EmployeeQuery setUserNames(List<String> userNames) {
    this.userNames = userNames;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public EmployeeQuery setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }
}
