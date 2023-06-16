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
