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

import org.kayura.uasp.file.FileLinkVo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TrackCommentVo {

  private CommentTypes type;
  private String taskId;
  private String taskKey;
  private String taskName;
  private String userId;
  private String userName;
  private String comment;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private Long duration;
  private List<IdentityLinkVo> identityLinks = new ArrayList<>();
  private List<FileLinkVo> attachments = new ArrayList<>();

  public static TrackCommentVo create() {
    return new TrackCommentVo();
  }

  public String getUserId() {
    return userId;
  }

  public TrackCommentVo setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public TrackCommentVo setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
    return this;
  }

  public String getComment() {
    return comment;
  }

  public TrackCommentVo setComment(String comment) {
    this.comment = comment;
    return this;
  }

  public String getTaskId() {
    return taskId;
  }

  public TrackCommentVo setTaskId(String taskId) {
    this.taskId = taskId;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public TrackCommentVo setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getTaskName() {
    return taskName;
  }

  public TrackCommentVo setTaskName(String taskName) {
    this.taskName = taskName;
    return this;
  }

  public LocalDateTime getEndTime() {
    return endTime;
  }

  public TrackCommentVo setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
    return this;
  }

  public Long getDuration() {
    return duration;
  }

  public TrackCommentVo setDuration(Long duration) {
    this.duration = duration;
    return this;
  }

  public TrackCommentVo addUser(String id, String name) {
    this.identityLinks.add(IdentityLinkVo.create().setUserId(id).setUserName(name));
    return this;
  }

  public TrackCommentVo addGroup(String id, String name) {
    this.identityLinks.add(IdentityLinkVo.create().setGroupId(id).setGroupName(name));
    return this;
  }


  public List<IdentityLinkVo> getIdentityLinks() {
    return identityLinks;
  }

  public TrackCommentVo setIdentityLinks(List<IdentityLinkVo> identityLinks) {
    this.identityLinks = identityLinks;
    return this;
  }

  public CommentTypes getType() {
    return type;
  }

  public TrackCommentVo setType(CommentTypes type) {
    this.type = type;
    return this;
  }

  public String getTaskKey() {
    return taskKey;
  }

  public TrackCommentVo setTaskKey(String taskKey) {
    this.taskKey = taskKey;
    return this;
  }

  public List<FileLinkVo> getAttachments() {
    return attachments;
  }

  public TrackCommentVo setAttachments(List<FileLinkVo> attachments) {
    this.attachments = attachments;
    return this;
  }
}
