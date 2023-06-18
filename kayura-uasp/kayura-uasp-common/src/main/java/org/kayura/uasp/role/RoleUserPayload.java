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

package org.kayura.uasp.role;

import javax.validation.constraints.NotBlank;

public class RoleUserPayload {

  @NotBlank
  private String userId;
  @NotBlank
  private String roleId;

  public static RoleUserPayload create() {
    return new RoleUserPayload();
  }

  public String getUserId() {
    return userId;
  }

  public RoleUserPayload setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getRoleId() {
    return roleId;
  }

  public RoleUserPayload setRoleId(String roleId) {
    this.roleId = roleId;
    return this;
  }
}
