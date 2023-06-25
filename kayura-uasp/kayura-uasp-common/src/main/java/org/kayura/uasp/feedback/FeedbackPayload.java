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

import org.kayura.type.StringList;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;

public class FeedbackPayload {

  @NotBlank(groups = Update.class)
  private String postId;
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

}
