package org.kayura.uasp.organize.entity;

import org.kayura.mybatis.annotation.mapper.Table;

@Table("uasp_membership")
public class MembershipEntity {

  private String userId;
  private String groupId;

  public static MembershipEntity create() {
    return new MembershipEntity();
  }

  public String getUserId() {
    return userId;
  }

  public MembershipEntity setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getGroupId() {
    return groupId;
  }

  public MembershipEntity setGroupId(String groupId) {
    this.groupId = groupId;
    return this;
  }
}
