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

package org.kayura.security.utils;

import org.kayura.security.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public abstract class UserContext {

  /**
   * 通过使用SecurityContextHolder从安全上下文中获取当前认证的Authentication对象。
   * 然后，它检查Authentication对象的principal属性是否为LoginUser类型，
   * 如果是，则将其转换为LoginUser对象并返回。
   * <p>
   * 这种实现假设你的应用程序使用了Spring Security来处理身份验证和授权，并且已经配置了相应的认证机制。
   * SecurityContextHolder是Spring Security的上下文持有者，用于访问当前认证的信息。
   *
   * @return 当前登录用户 {@link LoginUser}
   */
  public static LoginUser loginUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      Object principal = auth.getPrincipal();
      if (principal instanceof LoginUser) {
        return (LoginUser) principal;
      }
    }
    return null;
  }

  public static boolean hasRootOrAdmin() {
    return Objects.requireNonNull(loginUser()).hasRootOrAdmin();
  }

}
