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

package org.kayura.uasp.feedback;

import org.kayura.uasp.file.FileLinkVo;

import java.time.LocalDateTime;
import java.util.List;

/** 返回主题内容 */
public class FeedbackVo {

  private String feedbackId;
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

  public String getFeedbackId() {
    return feedbackId;
  }

  public FeedbackVo setFeedbackId(String feedbackId) {
    this.feedbackId = feedbackId;
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
}
