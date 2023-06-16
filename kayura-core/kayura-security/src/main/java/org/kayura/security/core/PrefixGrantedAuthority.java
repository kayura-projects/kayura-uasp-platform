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
