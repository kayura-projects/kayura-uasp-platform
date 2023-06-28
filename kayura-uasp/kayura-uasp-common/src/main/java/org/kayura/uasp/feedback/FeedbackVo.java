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

package org.kayura.uasp.feedback;

import org.kayura.uasp.file.FileLinkVo;

import java.time.LocalDateTime;
import java.util.List;

/** 返回主题内容 */
public class FeedbackVo {

  private String postId;
  private String appId;
  private String appName;
  private String title;
  private String content;
  private String authorId;
  private String authorName;
  private LocalDateTime postTime;
  private Boolean solved;
  private PostStatus status;
  private LocalDateTime lastlyTime;

  private List<FileLinkVo> attachments;
  private List<FeedbackReplyVo> replays;

  public static FeedbackVo create() {
    return new FeedbackVo();
  }

  public String getPostId() {
    return postId;
  }

  public FeedbackVo setPostId(String postId) {
    this.postId = postId;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public FeedbackVo setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getContent() {
    return content;
  }

  public FeedbackVo setContent(String content) {
    this.content = content;
    return this;
  }

  public List<FileLinkVo> getAttachments() {
    return attachments;
  }

  public FeedbackVo setAttachments(List<FileLinkVo> attachments) {
    this.attachments = attachments;
    return this;
  }

  public String getAuthorId() {
    return authorId;
  }

  public FeedbackVo setAuthorId(String authorId) {
    this.authorId = authorId;
    return this;
  }

  public LocalDateTime getPostTime() {
    return postTime;
  }

  public FeedbackVo setPostTime(LocalDateTime postTime) {
    this.postTime = postTime;
    return this;
  }

  public Boolean getSolved() {
    return solved;
  }

  public FeedbackVo setSolved(Boolean solved) {
    this.solved = solved;
    return this;
  }

  public PostStatus getStatus() {
    return status;
  }

  public FeedbackVo setStatus(PostStatus status) {
    this.status = status;
    return this;
  }

  public LocalDateTime getLastlyTime() {
    return lastlyTime;
  }

  public FeedbackVo setLastlyTime(LocalDateTime lastlyTime) {
    this.lastlyTime = lastlyTime;
    return this;
  }

  public List<FeedbackReplyVo> getReplays() {
    return replays;
  }

  public FeedbackVo setReplays(List<FeedbackReplyVo> replays) {
    this.replays = replays;
    return this;
  }

  public String getAuthorName() {
    return authorName;
  }

  public FeedbackVo setAuthorName(String authorName) {
    this.authorName = authorName;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public FeedbackVo setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getAppName() {
    return appName;
  }

  public FeedbackVo setAppName(String appName) {
    this.appName = appName;
    return this;
  }
}
