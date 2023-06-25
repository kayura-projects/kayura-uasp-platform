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

import java.time.LocalDateTime;

public class FeedbackReplyVo {

  private String postId;
  private String content;
  private String authorId;
  private String authorName;
  private LocalDateTime postTime;

  public static FeedbackReplyVo create() {
    return new FeedbackReplyVo();
  }

  public String getPostId() {
    return postId;
  }

  public FeedbackReplyVo setPostId(String postId) {
    this.postId = postId;
    return this;
  }

  public String getContent() {
    return content;
  }

  public FeedbackReplyVo setContent(String content) {
    this.content = content;
    return this;
  }

  public String getAuthorId() {
    return authorId;
  }

  public FeedbackReplyVo setAuthorId(String authorId) {
    this.authorId = authorId;
    return this;
  }

  public String getAuthorName() {
    return authorName;
  }

  public FeedbackReplyVo setAuthorName(String authorName) {
    this.authorName = authorName;
    return this;
  }

  public LocalDateTime getPostTime() {
    return postTime;
  }

  public FeedbackReplyVo setPostTime(LocalDateTime postTime) {
    this.postTime = postTime;
    return this;
  }
}
