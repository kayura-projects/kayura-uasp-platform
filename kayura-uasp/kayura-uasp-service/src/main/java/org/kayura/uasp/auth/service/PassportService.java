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
