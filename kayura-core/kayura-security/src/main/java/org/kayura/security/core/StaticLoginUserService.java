/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ 版权所属 2019 Liang.Xia 及 原作者
 ~ 您可以在 Apache License 2.0 版下获得许可副本，
 ~ 同时必须保证分发的本软件是在“原始”的基础上分发的。
 ~ 除非适用法律要求或书面同意。
 ~
 ~ http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ 请参阅许可证中控制权限和限制的特定语言。
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

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
