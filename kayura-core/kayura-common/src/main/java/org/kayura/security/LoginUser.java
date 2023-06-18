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
