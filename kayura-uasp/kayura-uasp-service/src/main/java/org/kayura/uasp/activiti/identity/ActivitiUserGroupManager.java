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
