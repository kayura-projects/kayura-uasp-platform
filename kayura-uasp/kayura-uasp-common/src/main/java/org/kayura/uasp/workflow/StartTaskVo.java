package org.kayura.uasp.workflow;

import java.util.ArrayList;
import java.util.List;

public class StartTaskVo {

  private List<ChooseFlowVo> flows;
  private List<TaskTrackVo> tasks;
  private List<UsedOpinionVo> opinions;

  public static StartTaskVo create() {
    return new StartTaskVo();
  }

  public List<ChooseFlowVo> getFlows() {
    return flows;
  }

  public StartTaskVo setFlows(List<ChooseFlowVo> flows) {
    this.flows = flows;
    return this;
  }

  public List<TaskTrackVo> getTasks() {
    return tasks;
  }

  public StartTaskVo setTasks(List<TaskTrackVo> tasks) {
    this.tasks = tasks;
    return this;
  }

  public StartTaskVo addTask(TaskTrackVo task) {
    if (this.tasks == null) {
      this.tasks = new ArrayList<>();
    }
    this.tasks.add(task);
    return this;
  }

  public List<UsedOpinionVo> getOpinions() {
    return opinions;
  }

  public StartTaskVo setOpinions(List<UsedOpinionVo> opinions) {
    this.opinions = opinions;
    return this;
  }
}
