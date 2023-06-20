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
import org.kayura.uasp.privilege.PrivilegeTypes;

public class QueryPrivilegeCommand extends ApiCommand {

  private PrivilegeTypes type;
  private String appId;
  private String linkId;
  private boolean authScope;

  public PrivilegeTypes getType() {
    return type;
  }

  public QueryPrivilegeCommand setType(PrivilegeTypes type) {
    this.type = type;
    return this;
  }

  public String getLinkId() {
    return linkId;
  }

  public QueryPrivilegeCommand setLinkId(String linkId) {
    this.linkId = linkId;
    return this;
  }

  public boolean isAuthScope() {
    return authScope;
  }

  public QueryPrivilegeCommand setAuthScope(boolean authScope) {
    this.authScope = authScope;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public QueryPrivilegeCommand setAppId(String appId) {
    this.appId = appId;
    return this;
  }
}
