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

package org.kayura.security.web;

import org.kayura.security.core.SecuredItem;
import org.kayura.utils.CollectionUtils;
import org.springframework.security.access.AccessDeniedException;

import java.util.List;

public class PermissionDeniedException extends AccessDeniedException {

  private List<SecuredItem> securedItems;

  public PermissionDeniedException(String message, List<SecuredItem> securedItems) {
    super(message);
    this.securedItems = securedItems;
  }

  public static PermissionDeniedException create(String message, List<SecuredItem> securedItems) {
    return new PermissionDeniedException(message, securedItems);
  }

  public List<SecuredItem> getSecuredItems() {
    return securedItems;
  }

  public PermissionDeniedException setSecuredItems(List<SecuredItem> securedItems) {
    this.securedItems = securedItems;
    return this;
  }

  @Override
  public String getMessage() {

    StringBuilder sb = new StringBuilder();
    sb.append(super.getMessage());
    for (SecuredItem securedItem : securedItems) {
      sb.append(securedItem.getResource());
      if (CollectionUtils.isNotEmpty(securedItem.getActions())) {
        sb.append("#");
        sb.append(String.join(",", securedItem.getActions()));
      }
      sb.append(";");
    }
    return sb.toString();
  }

}
