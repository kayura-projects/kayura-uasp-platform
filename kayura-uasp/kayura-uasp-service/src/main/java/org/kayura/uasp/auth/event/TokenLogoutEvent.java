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

package org.kayura.uasp.auth.event;

import org.kayura.event.Event;
import org.kayura.security.LoginUser;

public class TokenLogoutEvent extends Event {

  private final LoginUser loginUser;
  private final String token;

  public TokenLogoutEvent(Object source, LoginUser loginUser, String token) {
    super(source);
    this.loginUser = loginUser;
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public LoginUser getLoginUser() {
    return loginUser;
  }
}
