/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
