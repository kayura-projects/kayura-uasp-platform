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
