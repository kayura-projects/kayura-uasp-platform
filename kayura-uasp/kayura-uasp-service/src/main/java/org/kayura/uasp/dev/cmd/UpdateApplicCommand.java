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

package org.kayura.uasp.dev.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.applic.ApplicPayload;

public class UpdateApplicCommand extends Command {

  private String appId;
  private ApplicPayload payload;

  public String getAppId() {
    return appId;
  }

  public UpdateApplicCommand setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public ApplicPayload getPayload() {
    return payload;
  }

  public UpdateApplicCommand setPayload(ApplicPayload payload) {
    this.payload = payload;
    return this;
  }
}
