/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.basic.entity;

import org.kayura.mybatis.annotation.mapper.ForeignKey;
import org.kayura.mybatis.annotation.mapper.Id;
import org.kayura.mybatis.annotation.mapper.RefColumn;
import org.kayura.mybatis.annotation.mapper.Table;
import org.kayura.type.StringList;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.feedback.PostStatus;

import java.time.LocalDateTime;

/**
 * 问题反馈(uasp_feedback) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_feedback")
public class FeedbackEntity {

  /** 提交ID */
  @Id
  private String postId;
  /** 应用ID */
  private String appId;
  /** 主题ID */
  private String subjectId;
  /** 归类 */
  private String category;
  /** 标题 */
  private String title;
  /** 内容 */
  private String content;
  /** 附件IDS */
  private StringList attachmentIds;
  /** 填写人ID */
  @ForeignKey(entity = UserEntity.class, alias = "uu", pkName = "user_id_")
  private String authorId;
  @RefColumn(from = "uu", value = "display_name_")
  private String authorName;
  /** 发表时间 */
  private LocalDateTime postTime;
  /** 最后时间 */
  private LocalDateTime lastlyTime;
  /** 是否解决 */
  private Boolean solved;
  /** 状态:D未发布,V已发布,C已关闭; */
  private PostStatus status;

  public static FeedbackEntity create() {
    return new FeedbackEntity();
  }

  public String getPostId() {
    return postId;
  }

  public FeedbackEntity setPostId(String postId) {
    this.postId = postId;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public FeedbackEntity setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getSubjectId() {
    return subjectId;
  }

  public FeedbackEntity setSubjectId(String subjectId) {
    this.subjectId = subjectId;
    return this;
  }

  public String getCategory() {
    return category;
  }

  public FeedbackEntity setCategory(String category) {
    this.category = category;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public FeedbackEntity setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getContent() {
    return content;
  }

  public FeedbackEntity setContent(String content) {
    this.content = content;
    return this;
  }

  public StringList getAttachmentIds() {
    return attachmentIds;
  }

  public FeedbackEntity setAttachmentIds(StringList attachmentIds) {
    this.attachmentIds = attachmentIds;
    return this;
  }

  public String getAuthorId() {
    return authorId;
  }

  public FeedbackEntity setAuthorId(String authorId) {
    this.authorId = authorId;
    return this;
  }

  public LocalDateTime getPostTime() {
    return postTime;
  }

  public FeedbackEntity setPostTime(LocalDateTime postTime) {
    this.postTime = postTime;
    return this;
  }

  public Boolean getSolved() {
    return solved;
  }

  public FeedbackEntity setSolved(Boolean solved) {
    this.solved = solved;
    return this;
  }

  public PostStatus getStatus() {
    return status;
  }

  public FeedbackEntity setStatus(PostStatus status) {
    this.status = status;
    return this;
  }

  public String getAuthorName() {
    return authorName;
  }

  public FeedbackEntity setAuthorName(String authorName) {
    this.authorName = authorName;
    return this;
  }

  public LocalDateTime getLastlyTime() {
    return lastlyTime;
  }

  public FeedbackEntity setLastlyTime(LocalDateTime lastlyTime) {
    this.lastlyTime = lastlyTime;
    return this;
  }
}