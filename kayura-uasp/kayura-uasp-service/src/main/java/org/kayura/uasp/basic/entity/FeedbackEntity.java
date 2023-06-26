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
  /** 标记:1 提问;2 回复; */
  private Integer flag;
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

  public Integer getFlag() {
    return flag;
  }

  public FeedbackEntity setFlag(Integer flag) {
    this.flag = flag;
    return this;
  }
}