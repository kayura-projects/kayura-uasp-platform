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
