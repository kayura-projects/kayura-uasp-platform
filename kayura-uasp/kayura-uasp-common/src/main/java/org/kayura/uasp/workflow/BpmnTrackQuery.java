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

package org.kayura.uasp.workflow;

import javax.validation.constraints.NotBlank;

public class BpmnTrackQuery {

  @NotBlank
  private String businessKey;

  public static BpmnTrackQuery create() {
    return new BpmnTrackQuery();
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public BpmnTrackQuery setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }
}
