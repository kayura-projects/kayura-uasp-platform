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

package org.kayura.uasp.user;

import org.kayura.type.EnumValue;

/** 用户状态:APPLY_ING 申请,ENABLED 启用,DISABLED 禁用; */
public enum UserStatus implements EnumValue {

  APPLY_ING("A", "申请中"),
  ENABLED("E", "启用"),
  DISABLED("D", "禁用");

  private final String value;
  private final String name;

  UserStatus(final String value, String name) {
    this.value = value;
    this.name = name;
  }

  @Override
  public String toString() {
    return this.value;
  }

  public String getName() {
    return name;
  }
}
