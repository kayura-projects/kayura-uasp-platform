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
