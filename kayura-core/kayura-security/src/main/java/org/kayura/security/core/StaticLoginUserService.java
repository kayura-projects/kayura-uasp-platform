/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
