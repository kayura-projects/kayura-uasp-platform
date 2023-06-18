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
