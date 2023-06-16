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
