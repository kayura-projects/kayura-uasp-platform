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
