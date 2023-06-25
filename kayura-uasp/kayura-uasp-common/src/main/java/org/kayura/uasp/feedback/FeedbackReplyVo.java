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
