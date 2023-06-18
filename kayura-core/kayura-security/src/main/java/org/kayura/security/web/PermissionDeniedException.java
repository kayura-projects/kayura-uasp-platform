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
