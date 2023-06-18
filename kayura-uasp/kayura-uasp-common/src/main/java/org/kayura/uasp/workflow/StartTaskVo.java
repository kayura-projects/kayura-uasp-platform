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
