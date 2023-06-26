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

import org.kayura.type.StringList;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;

public class FeedbackPayload {

  @NotBlank(groups = Update.class)
  private String postId;
  private String appId;
  private String subjectId;
  private String category;
  @NotBlank
  private String title;
  @NotBlank
  private String content;
  private StringList attachmentIds;

  public static FeedbackPayload of(ReplyPayload payload) {
    return create()
      .setSubjectId(payload.getSubjectId())
      .setContent(payload.getContent())
      .setAttachmentIds(payload.getAttachmentIds());
  }

  public static FeedbackPayload create() {
    return new FeedbackPayload();
  }

  public String getPostId() {
    return postId;
  }

  public FeedbackPayload setPostId(String postId) {
    this.postId = postId;
    return this;
  }

  public String getSubjectId() {
    return subjectId;
  }

  public FeedbackPayload setSubjectId(String subjectId) {
    this.subjectId = subjectId;
    return this;
  }

  public String getCategory() {
    return category;
  }

  public FeedbackPayload setCategory(String category) {
    this.category = category;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public FeedbackPayload setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getContent() {
    return content;
  }

  public FeedbackPayload setContent(String content) {
    this.content = content;
    return this;
  }

  public StringList getAttachmentIds() {
    return attachmentIds;
  }

  public FeedbackPayload setAttachmentIds(StringList attachmentIds) {
    this.attachmentIds = attachmentIds;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public FeedbackPayload setAppId(String appId) {
    this.appId = appId;
    return this;
  }
}
