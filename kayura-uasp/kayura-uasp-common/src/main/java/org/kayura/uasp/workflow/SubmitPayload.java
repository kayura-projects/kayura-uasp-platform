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

public class SubmitPayload {

  @NotBlank
  private String taskId;
  private String target; // 表示流转至目标的（节点ID）
  private String handler;
  private String comment;
  private List<NextTaskFrm> nextTasks;  // 指示下个任务的环节与处理人
  private Map<String, Object> variables;

  public static SubmitPayload create() {
    return new SubmitPayload();
  }

  public String getTaskId() {
    return taskId;
  }

  public SubmitPayload setTaskId(String taskId) {
    this.taskId = taskId;
    return this;
  }

  public String getTarget() {
    return target;
  }

  public SubmitPayload setTarget(String target) {
    this.target = target;
    return this;
  }

  public String getHandler() {
    return handler;
  }

  public SubmitPayload setHandler(String handler) {
    this.handler = handler;
    return this;
  }

  public String getComment() {
    return comment;
  }

  public SubmitPayload setComment(String comment) {
    this.comment = comment;
    return this;
  }

  public Map<String, Object> getVariables() {
    return variables;
  }

  public SubmitPayload setVariables(Map<String, Object> variables) {
    this.variables = variables;
    return this;
  }

  public List<NextTaskFrm> getNextTasks() {
    return nextTasks;
  }

  public SubmitPayload setNextTasks(List<NextTaskFrm> nextTasks) {
    this.nextTasks = nextTasks;
    return this;
  }
}
