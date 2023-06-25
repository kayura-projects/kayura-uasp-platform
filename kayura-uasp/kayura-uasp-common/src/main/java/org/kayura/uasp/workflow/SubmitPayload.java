/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
