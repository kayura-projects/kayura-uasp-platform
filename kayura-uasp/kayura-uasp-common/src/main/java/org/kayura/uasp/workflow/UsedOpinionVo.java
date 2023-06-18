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

package org.kayura.uasp.workflow;

import java.time.LocalDateTime;

public class UsedOpinionVo {

  private String commentId;
  private String content;
  private Integer useCount;
  private LocalDateTime useTime;

  public static UsedOpinionVo create() {
    return new UsedOpinionVo();
  }

  public String getCommentId() {
    return commentId;
  }

  public UsedOpinionVo setCommentId(String commentId) {
    this.commentId = commentId;
    return this;
  }

  public String getContent() {
    return content;
  }

  public UsedOpinionVo setContent(String content) {
    this.content = content;
    return this;
  }

  public Integer getUseCount() {
    return useCount;
  }

  public UsedOpinionVo setUseCount(Integer useCount) {
    this.useCount = useCount;
    return this;
  }

  public LocalDateTime getUseTime() {
    return useTime;
  }

  public UsedOpinionVo setUseTime(LocalDateTime useTime) {
    this.useTime = useTime;
    return this;
  }
}
