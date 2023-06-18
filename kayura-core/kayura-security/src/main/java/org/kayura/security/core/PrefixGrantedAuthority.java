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
