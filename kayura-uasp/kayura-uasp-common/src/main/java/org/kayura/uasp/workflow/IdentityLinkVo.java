/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
