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

package org.kayura.security.core;

import org.kayura.utils.StringUtils;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;

public class PrefixGrantedAuthority implements GrantedAuthority {

  @Serial
  private static final long serialVersionUID = 1L;
  private String prefix;
  private String authority;

  public static PrefixGrantedAuthority create() {
    return new PrefixGrantedAuthority();
  }

  public static PrefixGrantedAuthority roleOf(String roleId) {
    return create().setPrefix("ROLE_").setAuthority(roleId);
  }

  public static PrefixGrantedAuthority groupOf(String groupId) {
    return create().setPrefix("GROUP_").setAuthority(groupId);
  }

  public PrefixGrantedAuthority() {
  }

  public PrefixGrantedAuthority setAuthority(String authority) {
    this.authority = authority;
    return this;
  }

  @Override
  public String getAuthority() {
    StringBuilder sb = new StringBuilder();
    if (StringUtils.hasText(prefix)) {
      sb.append(prefix).append("_");
    }
    sb.append(authority);
    return sb.toString();
  }

  public PrefixGrantedAuthority setPrefix(String prefix) {
    this.prefix = prefix;
    return this;
  }
}
