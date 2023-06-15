package org.kayura.uasp.workflow;

public class TaskFlowVo {

  private String title;
  private Integer status; // 1 完成，0 当前，-1未到达.

  public static TaskFlowVo create() {
    return new TaskFlowVo();
  }

  public String getTitle() {
    return title;
  }

  public TaskFlowVo setTitle(String title) {
    this.title = title;
    return this;
  }

  public Integer getStatus() {
    return status;
  }

  public TaskFlowVo setStatus(Integer status) {
    this.status = status;
    return this;
  }
}
