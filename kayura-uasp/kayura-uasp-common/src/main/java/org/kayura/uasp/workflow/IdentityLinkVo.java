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

import com.fasterxml.jackson.annotation.JsonIgnore;

public class IdentityLinkVo {

  private String type;
  private String userId;
  private String userName;
  private String groupId;
  private String groupName;

  public static IdentityLinkVo create() {
    return new IdentityLinkVo();
  }

  @JsonIgnore
  public boolean isUser() {
    return userId != null;
  }

  @JsonIgnore
  public boolean isGroup() {
    return groupId != null;
  }

  public String getUserId() {
    return userId;
  }

  public IdentityLinkVo setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public IdentityLinkVo setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getGroupId() {
    return groupId;
  }

  public IdentityLinkVo setGroupId(String groupId) {
    this.groupId = groupId;
    return this;
  }

  public String getGroupName() {
    return groupName;
  }

  public IdentityLinkVo setGroupName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  public String getType() {
    return type;
  }

  public IdentityLinkVo setType(String type) {
    this.type = type;
    return this;
  }
}
