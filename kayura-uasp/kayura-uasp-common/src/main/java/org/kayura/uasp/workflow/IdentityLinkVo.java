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
