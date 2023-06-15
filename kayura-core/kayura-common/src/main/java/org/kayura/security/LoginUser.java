package org.kayura.security;

import org.kayura.type.UserTypes;

import java.time.LocalDateTime;

public interface LoginUser extends LoginUserId {

  String getAppId();

  String getUserId();

  String getUsername();

  String getDisplayName();

  String getMobile();

  String getTenantId();

  String getCompanyId();

  UserTypes getUserType();

  boolean hasPermission(String resource, String... actions);

  boolean hasAnyRole(String... roles);

  /** 如果是后台管理人员 */
  boolean hasRootOrAdmin();

  /** 如果是租户下的业务人员 */
  boolean hasTenantUser();

  LocalDateTime getLoginTime();

  LocalDateTime getActivatedTime();
}
