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
