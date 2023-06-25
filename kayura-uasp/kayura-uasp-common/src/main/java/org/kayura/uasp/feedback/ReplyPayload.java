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

import javax.validation.constraints.NotBlank;

public class ReplyPayload {

  @NotBlank
  private String subjectId;
  @NotBlank
  private String content;
  private StringList attachmentIds;

  public static ReplyPayload create() {
    return new ReplyPayload();
  }

  public String getSubjectId() {
    return subjectId;
  }

  public ReplyPayload setSubjectId(String subjectId) {
    this.subjectId = subjectId;
    return this;
  }

  public String getContent() {
    return content;
  }

  public ReplyPayload setContent(String content) {
    this.content = content;
    return this;
  }

  public StringList getAttachmentIds() {
    return attachmentIds;
  }

  public ReplyPayload setAttachmentIds(StringList attachmentIds) {
    this.attachmentIds = attachmentIds;
    return this;
  }
}
