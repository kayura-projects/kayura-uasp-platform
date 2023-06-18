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

package org.kayura.uasp.activiti.identity;

import org.kayura.uasp.organize.entity.MembershipEntity;
import org.kayura.uasp.organize.manage.MembershipManager;
import org.activiti.api.runtime.shared.identity.UserGroupManager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActivitiUserGroupManager implements UserGroupManager {

  private final MembershipManager membershipManager;

  public ActivitiUserGroupManager(MembershipManager membershipManager) {
    this.membershipManager = membershipManager;
  }

  @Override
  public List<String> getUserGroups(String userId) {
    List<String> groupIds = membershipManager.selectList(w -> {
      w.select(MembershipEntity::getGroupId);
      w.eq(MembershipEntity::getUserId, userId);
    }).stream().map(MembershipEntity::getGroupId).collect(Collectors.toList());
    return groupIds;
  }

  @Override
  public List<String> getUserRoles(String userId) {
    return null;
  }

  @Override
  public List<String> getGroups() {
    return null;
  }

  @Override
  public List<String> getUsers() {
    return null;
  }
}
