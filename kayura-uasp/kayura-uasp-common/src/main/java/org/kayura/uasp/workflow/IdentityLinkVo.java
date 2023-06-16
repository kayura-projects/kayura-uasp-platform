/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
