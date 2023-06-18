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
