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

package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.role.RoleTypes;
import org.kayura.uasp.utils.OutputTypes;

public class CandidateRoleCommand extends Command {

  private OutputTypes output;
  private String userId;
  private String appId;
  private String tenantId;
  private RoleTypes roleType;

  public OutputTypes getOutput() {
    return output;
  }

  public CandidateRoleCommand setOutput(OutputTypes output) {
    this.output = output;
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public CandidateRoleCommand setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public CandidateRoleCommand setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public CandidateRoleCommand setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public RoleTypes getRoleType() {
    return roleType;
  }

  public CandidateRoleCommand setRoleType(RoleTypes roleType) {
    this.roleType = roleType;
    return this;
  }
}
