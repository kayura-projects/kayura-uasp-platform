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
