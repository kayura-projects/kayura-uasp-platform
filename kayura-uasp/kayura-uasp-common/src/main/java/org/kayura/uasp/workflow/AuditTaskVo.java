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

import org.kayura.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuditTaskVo {

  private String definitionId;
  private String instanceId;
  private String instanceName;
  private String startUserId;
  private List<TaskTrackVo> tasks = new ArrayList<>();
  private List<TrackCommentVo> comments = new ArrayList<>();
  private List<UsedOpinionVo> opinions;

  public static AuditTaskVo create() {
    return new AuditTaskVo();
  }

  public String getDefinitionId() {
    return definitionId;
  }

  public AuditTaskVo setDefinitionId(String definitionId) {
    this.definitionId = definitionId;
    return this;
  }

  public String getInstanceId() {
    return instanceId;
  }

  public AuditTaskVo setInstanceId(String instanceId) {
    this.instanceId = instanceId;
    return this;
  }

  public List<TaskTrackVo> getTasks() {
    return tasks;
  }

  public AuditTaskVo setTasks(List<TaskTrackVo> tasks) {
    this.tasks = tasks;
    return this;
  }

  public AuditTaskVo addTask(TaskTrackVo task) {
    this.tasks.add(task);
    return this;
  }

  public List<TrackCommentVo> getComments() {
    return comments;
  }

  public AuditTaskVo setComments(List<TrackCommentVo> comments) {
    this.comments = comments;
    return this;
  }

  public AuditTaskVo addComment(TrackCommentVo comment) {
    this.comments.add(comment);
    return this;
  }

  public AuditTaskVo addComments(Collection<TrackCommentVo> comments) {
    if (CollectionUtils.isNotEmpty(comments)) {
      this.comments.addAll(comments);
    }
    return this;
  }

  public String getInstanceName() {
    return instanceName;
  }

  public AuditTaskVo setInstanceName(String instanceName) {
    this.instanceName = instanceName;
    return this;
  }

  public String getStartUserId() {
    return startUserId;
  }

  public AuditTaskVo setStartUserId(String startUserId) {
    this.startUserId = startUserId;
    return this;
  }

  public List<UsedOpinionVo> getOpinions() {
    return opinions;
  }

  public AuditTaskVo setOpinions(List<UsedOpinionVo> opinions) {
    this.opinions = opinions;
    return this;
  }
}
