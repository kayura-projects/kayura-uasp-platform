/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ 版权所属 2019 Liang.Xia 及 原作者
 ~ 您可以在 Apache License 2.0 版下获得许可副本，
 ~ 同时必须保证分发的本软件是在“原始”的基础上分发的。
 ~ 除非适用法律要求或书面同意。
 ~
 ~ http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ 请参阅许可证中控制权限和限制的特定语言。
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

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
