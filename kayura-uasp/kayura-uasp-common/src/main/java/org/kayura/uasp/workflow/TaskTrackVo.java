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

import org.kayura.type.Properties;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskTrackVo {

  private String processKey;
  private String definitionId;
  private String title;
  private String taskId;
  private String taskKey;
  private String taskName;
  private LocalDateTime startTime;
  private String formKey;
  private Properties properties;
  private List<NextTaskVo> nextTasks = new ArrayList<>();

  public static TaskTrackVo create() {
    return new TaskTrackVo();
  }

  public String getTitle() {
    return title;
  }

  public TaskTrackVo setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getTaskId() {
    return taskId;
  }

  public TaskTrackVo setTaskId(String taskId) {
    this.taskId = taskId;
    return this;
  }

  public String getTaskName() {
    return taskName;
  }

  public TaskTrackVo setTaskName(String taskName) {
    this.taskName = taskName;
    return this;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public TaskTrackVo setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
    return this;
  }

  public String getFormKey() {
    return formKey;
  }

  public TaskTrackVo setFormKey(String formKey) {
    this.formKey = formKey;
    return this;
  }

  public Properties getProperties() {
    return properties;
  }

  public TaskTrackVo setProperties(Properties properties) {
    this.properties = properties;
    return this;
  }

  public List<NextTaskVo> getNextTasks() {
    return nextTasks;
  }

  public TaskTrackVo setNextTasks(List<NextTaskVo> nextTasks) {
    this.nextTasks = nextTasks;
    return this;
  }

  public TaskTrackVo addNextTask(NextTaskVo nextTask) {
    this.nextTasks.add(nextTask);
    return this;
  }

  public String getProcessKey() {
    return processKey;
  }

  public TaskTrackVo setProcessKey(String processKey) {
    this.processKey = processKey;
    return this;
  }

  public String getDefinitionId() {
    return definitionId;
  }

  public TaskTrackVo setDefinitionId(String definitionId) {
    this.definitionId = definitionId;
    return this;
  }

  public String getTaskKey() {
    return taskKey;
  }

  public TaskTrackVo setTaskKey(String taskKey) {
    this.taskKey = taskKey;
    return this;
  }
}
