package org.kayura.security;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PermissionList extends HashSet<Permission> {

  public static PermissionList of(Map<String, Set<String>> moduleActions) {
    PermissionList permissions = new PermissionList();
    moduleActions.forEach((k, v) -> permissions.add(Permission.builder().setResource(k).setActions(v)));
    return permissions;
  }

  public Map<String, Set<String>> toMap() {
    Map<String, Set<String>> moduleActions = new HashMap<>();
    this.forEach(row -> {
      moduleActions.put(row.getResource(), row.getActions());
    });
    return moduleActions;
  }
}
