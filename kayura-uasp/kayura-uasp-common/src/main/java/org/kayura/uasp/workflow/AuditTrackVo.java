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

import org.kayura.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuditTrackVo {

  private Boolean started;
  private Boolean ended;
  private String businessKey;
  private List<TaskTrackVo> tasks = new ArrayList<>();
  private List<UsedOpinionVo> opinions = new ArrayList<>();
  private FlowLabels flowLabels = new FlowLabels();

  // 未启动
  private List<ChooseFlowVo> flows = new ArrayList<>();

  // 已启动
  private String definitionId;
  private String instanceName;
  private List<TrackCommentVo> comments = new ArrayList<>();

  public static AuditTrackVo create() {
    return new AuditTrackVo();
  }

  public AuditTrackVo addTask(TaskTrackVo taskTrack) {
    if (taskTrack != null) {
      this.tasks.add(taskTrack);
    }
    return this;
  }

  public AuditTrackVo addTasks(List<TaskTrackVo> tasks) {
    if (CollectionUtils.isNotEmpty(tasks)) {
      this.tasks.addAll(tasks);
    }
    return this;
  }

  public AuditTrackVo addFlow(ChooseFlowVo chooseFlow) {
    if (chooseFlow != null) {
      this.flows.add(chooseFlow);
    }
    return this;
  }

  public AuditTrackVo addComment(TrackCommentVo comment) {
    if (comment != null) {
      this.comments.add(comment);
    }
    return this;
  }

  public AuditTrackVo addComments(Collection<TrackCommentVo> comments) {
    if (CollectionUtils.isNotEmpty(comments)) {
      this.comments.addAll(comments);
    }
    return this;
  }

  public Boolean getStarted() {
    return started;
  }

  public AuditTrackVo setStarted(Boolean started) {
    this.started = started;
    return this;
  }

  public List<ChooseFlowVo> getFlows() {
    return flows;
  }

  public AuditTrackVo setFlows(List<ChooseFlowVo> flows) {
    this.flows = flows;
    return this;
  }

  public List<TaskTrackVo> getTasks() {
    return tasks;
  }

  public AuditTrackVo setTasks(List<TaskTrackVo> tasks) {
    this.tasks = tasks;
    return this;
  }

  public List<UsedOpinionVo> getOpinions() {
    return opinions;
  }

  public AuditTrackVo setOpinions(List<UsedOpinionVo> opinions) {
    this.opinions = opinions;
    return this;
  }

  public String getDefinitionId() {
    return definitionId;
  }

  public AuditTrackVo setDefinitionId(String definitionId) {
    this.definitionId = definitionId;
    return this;
  }

  public String getInstanceName() {
    return instanceName;
  }

  public AuditTrackVo setInstanceName(String instanceName) {
    this.instanceName = instanceName;
    return this;
  }

  public List<TrackCommentVo> getComments() {
    return comments;
  }

  public AuditTrackVo setComments(List<TrackCommentVo> comments) {
    this.comments = comments;
    return this;
  }

  public FlowLabels getFlowLabels() {
    return flowLabels;
  }

  public AuditTrackVo setFlowLabels(FlowLabels flowLabels) {
    this.flowLabels = flowLabels;
    return this;
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public AuditTrackVo setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

  public Boolean getEnded() {
    return ended;
  }

  public AuditTrackVo setEnded(Boolean ended) {
    this.ended = ended;
    return this;
  }
}
