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

package org.kayura.uasp.auth.service;

import org.kayura.except.ExceptUtils;
import org.kayura.security.core.LoginUserImpl;
import org.kayura.security.core.LoginUserService;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PassportService implements LoginUserService {

  private final UserManager userManager;

  public PassportService(UserManager userManager) {
    this.userManager = userManager;
  }

  @Override
  public LoginUserImpl loadUserByUsername(String userName) {

    UserEntity entity = userManager.selectOne(w -> w.eq(UserEntity::getUserName, userName));
    loginCheck(entity);
    return makeLoginUser(entity);
  }

  @Override
  public LoginUserImpl loadUserByMobile(String mobile) {

    UserEntity entity = userManager.selectOne(w -> w.eq(UserEntity::getMobile, mobile));
    loginCheck(entity);
    return makeLoginUser(entity);
  }

  @Override
  public LoginUserImpl loadUserByUserId(String userId) {

    UserEntity entity = userManager.selectById(userId);
    loginCheck(entity);
    return makeLoginUser(entity);
  }

  protected void loginCheck(UserEntity user) {

    if (user == null) {
      ExceptUtils.security("输入的账号不存在。");
    }

    if (Boolean.FALSE.equals(user.getEnabled())) {
      ExceptUtils.security("账号已被禁用，无法登录系统。");
    }

    if (Boolean.TRUE.equals(user.getLocked())) {
      ExceptUtils.security("账号已被锁定，无法登录系统。");
    }

    LocalDate now = DateUtils.nowDate();
    boolean isAccountExpired = user.getAccountExpire() != null && user.getAccountExpire().isAfter(now);
    if (isAccountExpired) {
      ExceptUtils.security("账号已经过期，无法登录系统。");
    }

    boolean isCredentialsExpired = user.getPasswordExpire() != null && user.getPasswordExpire().isAfter(now);
    if (isCredentialsExpired) {
      ExceptUtils.security("账号密码已经过期，必须先修改密码后再登录。");
    }
  }

  protected LoginUserImpl makeLoginUser(UserEntity userEntity) {

    LoginUserImpl userImpl = LoginUserImpl.builder()
      .setUserId(userEntity.getUserId())
      .setTenantId(userEntity.getTenantId())
      .setTenantName(userEntity.getTenantName())
      .setUsername(userEntity.getUserName())
      .setPassword(userEntity.getPassword())
      .setDisplayName(userEntity.getDisplayName())
      .setAvatar(userEntity.getAvatar())
      .setMobile(userEntity.getMobile())
      .setUserType(userEntity.getUserType());

    // 计算状态
    LocalDate now = DateUtils.nowDate();
    boolean accountNonExpired = userEntity.getAccountExpire() == null || userEntity.getAccountExpire().isBefore(now);
    boolean passwordNonExpired = userEntity.getPasswordExpire() == null || userEntity.getPasswordExpire().isBefore(now);
    boolean accountNonLocked = Boolean.TRUE.equals(userEntity.getLocked());

    // 构建登录者
    userImpl.setAccountNonLocked(accountNonLocked)
      .setAccountNonExpired(accountNonExpired)
      .setCredentialsNonExpired(passwordNonExpired)
      .setEnabled(userEntity.getEnabled())
      .setLoginTime(DateUtils.now());
    return userImpl;
  }

}
