/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
