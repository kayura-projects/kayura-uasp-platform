/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.ApiCommand;
import org.kayura.uasp.role.RoleTypes;
import org.kayura.uasp.utils.OutputTypes;

public class CandidateRoleCommand extends ApiCommand {

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
