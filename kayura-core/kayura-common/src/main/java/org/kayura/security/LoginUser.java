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
