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
