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

package org.kayura.security.core;

import org.kayura.utils.StringUtils;
import org.springframework.security.core.GrantedAuthority;

public class PrefixGrantedAuthority implements GrantedAuthority {

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
