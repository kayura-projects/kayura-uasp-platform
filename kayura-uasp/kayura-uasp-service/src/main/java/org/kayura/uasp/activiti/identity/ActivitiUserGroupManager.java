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
