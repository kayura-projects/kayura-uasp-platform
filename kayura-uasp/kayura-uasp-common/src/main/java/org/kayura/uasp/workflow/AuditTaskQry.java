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

public class AuditTaskQry {

  @NotBlank
  private String businessKey;
  private String userId;
  private String taskId;

  public static AuditTaskQry create() {
    return new AuditTaskQry();
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public AuditTaskQry setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public AuditTaskQry setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getTaskId() {
    return taskId;
  }

  public AuditTaskQry setTaskId(String taskId) {
    this.taskId = taskId;
    return this;
  }

}
