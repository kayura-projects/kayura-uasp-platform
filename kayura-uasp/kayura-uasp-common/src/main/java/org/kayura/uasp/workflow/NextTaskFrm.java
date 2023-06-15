package org.kayura.uasp.workflow;

import java.util.List;

public class NextTaskFrm {

  private String nodeId;
  private List<String> userIds;
  private List<String> groupIds;

  public static NextTaskFrm create() {
    return new NextTaskFrm();
  }

  public String getNodeId() {
    return nodeId;
  }

  public NextTaskFrm setNodeId(String nodeId) {
    this.nodeId = nodeId;
    return this;
  }

  public List<String> getUserIds() {
    return userIds;
  }

  public NextTaskFrm setUserIds(List<String> userIds) {
    this.userIds = userIds;
    return this;
  }

  public List<String> getGroupIds() {
    return groupIds;
  }

  public NextTaskFrm setGroupIds(List<String> groupIds) {
    this.groupIds = groupIds;
    return this;
  }
}
