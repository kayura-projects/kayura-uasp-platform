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

import org.kayura.except.ExceptUtils;

import java.util.HashMap;
import java.util.Map;

public class StaticLoginUserService implements LoginUserService {

  private Map<String, String> userMap = new HashMap<>();

  @Override
  public LoginUserImpl loadUserByUsername(String userName) {

    if (!userMap.containsKey(userName)) {
      ExceptUtils.security("请求登录的用户名不存在。");
    }

    LoginUserImpl su = new LoginUserImpl();
    su.setUsername(userName);
    su.setPassword(userMap.get(userName));
    return su;
  }

  @Override
  public LoginUserImpl loadUserByMobile(String mobile) {
    return loadUserByUsername(mobile);
  }

  @Override
  public LoginUserImpl loadUserByUserId(String userId) {
    return loadUserByUsername(userId);
  }

  public void setUserMap(Map<String, String> userMap) {
    this.userMap = userMap;
  }
}
