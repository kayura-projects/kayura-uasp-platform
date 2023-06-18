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
