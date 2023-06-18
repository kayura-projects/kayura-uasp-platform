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

package org.kayura.uasp.privilege;

import java.util.List;

public class PrivilegeBody {

  private String appId;
  private String linkId;
  private PrivilegeTypes type;
  private List<ModuleAction> privileges;

  public static PrivilegeBody create() {
    return new PrivilegeBody();
  }

  public String getLinkId() {
    return linkId;
  }

  public PrivilegeBody setLinkId(String linkId) {
    this.linkId = linkId;
    return this;
  }

  public PrivilegeTypes getType() {
    return type;
  }

  public PrivilegeBody setType(PrivilegeTypes type) {
    this.type = type;
    return this;
  }

  public List<ModuleAction> getPrivileges() {
    return privileges;
  }

  public PrivilegeBody setPrivileges(List<ModuleAction> privileges) {
    this.privileges = privileges;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public PrivilegeBody setAppId(String appId) {
    this.appId = appId;
    return this;
  }
}
