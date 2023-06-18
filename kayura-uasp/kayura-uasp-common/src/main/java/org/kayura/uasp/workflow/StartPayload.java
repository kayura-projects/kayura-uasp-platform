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
import java.util.List;
import java.util.Map;

public class StartPayload {

  @NotBlank
  private String businessKey;
  private String tenantId;
  private String title;
  private String initiator;
  private String comment;
  @NotBlank
  private String processKey;
  private List<NextTaskFrm> nextTasks;
  private Map<String, Object> variables;

  public static StartPayload create() {
    return new StartPayload();
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public StartPayload setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public StartPayload setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public StartPayload setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getInitiator() {
    return initiator;
  }

  public StartPayload setInitiator(String initiator) {
    this.initiator = initiator;
    return this;
  }

  public String getComment() {
    return comment;
  }

  public StartPayload setComment(String comment) {
    this.comment = comment;
    return this;
  }

  public String getProcessKey() {
    return processKey;
  }

  public StartPayload setProcessKey(String processKey) {
    this.processKey = processKey;
    return this;
  }

  public Map<String, Object> getVariables() {
    return variables;
  }

  public StartPayload setVariables(Map<String, Object> variables) {
    this.variables = variables;
    return this;
  }

  public List<NextTaskFrm> getNextTasks() {
    return nextTasks;
  }

  public StartPayload setNextTasks(List<NextTaskFrm> nextTasks) {
    this.nextTasks = nextTasks;
    return this;
  }
}
