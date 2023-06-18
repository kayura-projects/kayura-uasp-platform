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

public class SecuredItem {

  private String resource;
  private String[] actions;
  private String[] roles;

  public boolean isValid() {
    return StringUtils.isNotBlank(this.resource);
  }

  public static SecuredItem builder() {
    return new SecuredItem();
  }

  public String getResource() {
    return resource;
  }

  public SecuredItem setResource(String resource) {
    this.resource = resource;
    return this;
  }

  public String[] getActions() {
    return actions;
  }

  public SecuredItem setActions(String[] actions) {
    this.actions = actions;
    return this;
  }

  public String[] getRoles() {
    return roles;
  }

  public SecuredItem setRoles(String[] roles) {
    this.roles = roles;
    return this;
  }
}
