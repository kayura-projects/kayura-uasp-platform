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

import org.kayura.mybatis.annotation.Ignore;
import org.kayura.mybatis.annotation.querier.Like;

public class ClientUserQuery {

  private Boolean enabled;
  private Boolean locked;
  @Like("userName,displayName,mobile")
  private String searchText;
  @Ignore
  private Boolean expired;

  public String getSearchText() {
    return searchText;
  }

  public ClientUserQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public ClientUserQuery setEnabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  public Boolean getLocked() {
    return locked;
  }

  public ClientUserQuery setLocked(Boolean locked) {
    this.locked = locked;
    return this;
  }

  public Boolean getExpired() {
    return expired;
  }

  public ClientUserQuery setExpired(Boolean expired) {
    this.expired = expired;
    return this;
  }
}
