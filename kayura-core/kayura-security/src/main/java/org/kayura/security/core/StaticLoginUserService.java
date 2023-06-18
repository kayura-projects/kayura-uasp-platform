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
