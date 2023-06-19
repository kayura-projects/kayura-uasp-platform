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

import org.kayura.cmd.Command;
import org.kayura.uasp.utils.OutputTypes;

public class ChooseTenantCommand extends Command {

  private OutputTypes outType;
  private boolean hasApp;
  private String appId;
  private boolean includeApplic;

  public OutputTypes getOutType() {
    return outType;
  }

  public ChooseTenantCommand setOutType(OutputTypes outType) {
    this.outType = outType;
    return this;
  }

  public boolean isHasApp() {
    return hasApp;
  }

  public ChooseTenantCommand setHasApp(boolean hasApp) {
    this.hasApp = hasApp;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public ChooseTenantCommand setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public boolean isIncludeApplic() {
    return includeApplic;
  }

  public ChooseTenantCommand setIncludeApplic(boolean includeApplic) {
    this.includeApplic = includeApplic;
    return this;
  }
}
