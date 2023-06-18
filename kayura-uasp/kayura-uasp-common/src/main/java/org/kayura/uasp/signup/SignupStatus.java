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

package org.kayura.uasp.signup;

import org.kayura.type.EnumValue;

/** 状态:APPLYING 申请中,PASSED 允许,REFUSED 拒绝; */
public enum SignupStatus implements EnumValue {

  APPLYING("APPLYING", "密码"),
  PASSED("PASSED", "允许"),
  REFUSED("REFUSED", "拒绝");

  private final String value;
  private final String name;

  SignupStatus(final String value,
               final String name) {
    this.value = value;
    this.name = name;
  }

  @Override
  public String toString() {
    return this.value;
  }

  @Override
  public String getName() {
    return name;
  }
}
