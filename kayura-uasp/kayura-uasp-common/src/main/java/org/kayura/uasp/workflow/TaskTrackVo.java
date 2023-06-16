/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
