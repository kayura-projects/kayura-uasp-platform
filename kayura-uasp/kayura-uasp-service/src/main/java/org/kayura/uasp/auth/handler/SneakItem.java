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

package org.kayura.uasp.auth.handler;

import java.io.Serializable;

public class SneakItem implements Serializable {

  private Long expire;
  private String user;
  private String caller;

  public static SneakItem create() {
    return new SneakItem();
  }

  public Long getExpire() {
    return expire;
  }

  public SneakItem setExpire(Long expire) {
    this.expire = expire;
    return this;
  }

  public String getUser() {
    return user;
  }

  public SneakItem setUser(String user) {
    this.user = user;
    return this;
  }

  public String getCaller() {
    return caller;
  }

  public SneakItem setCaller(String caller) {
    this.caller = caller;
    return this;
  }
}
