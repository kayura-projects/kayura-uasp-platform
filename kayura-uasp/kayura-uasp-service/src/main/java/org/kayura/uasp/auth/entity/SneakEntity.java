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

package org.kayura.uasp.auth.entity;

import org.kayura.mybatis.annotation.mapper.Id;
import org.kayura.mybatis.annotation.mapper.Table;

@Table("uasp_sneak")
public class SneakEntity {

  @Id
  private String token;
  private Long expire;
  private String user;
  private String caller;

  public static SneakEntity create() {
    return new SneakEntity();
  }

  public String getToken() {
    return token;
  }

  public SneakEntity setToken(String token) {
    this.token = token;
    return this;
  }

  public Long getExpire() {
    return expire;
  }

  public SneakEntity setExpire(Long expire) {
    this.expire = expire;
    return this;
  }

  public String getUser() {
    return user;
  }

  public SneakEntity setUser(String user) {
    this.user = user;
    return this;
  }

  public String getCaller() {
    return caller;
  }

  public SneakEntity setCaller(String caller) {
    this.caller = caller;
    return this;
  }
}
