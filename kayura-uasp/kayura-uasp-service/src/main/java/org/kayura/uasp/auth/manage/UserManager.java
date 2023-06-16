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

package org.kayura.uasp.auth.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.mapper.UserMapper;
import org.kayura.utils.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserManager extends CrudManagerImpl<UserMapper, UserEntity> {

  private final PasswordEncoder passwordEncoder;

  protected UserManager(UserMapper baseMapper,
                        PasswordEncoder passwordEncoder) {
    super(baseMapper);
    this.passwordEncoder = passwordEncoder;
  }

  public void lockUser(String userId) {
    this.updateByWhere(w -> {
      w.set(UserEntity::getLocked, Boolean.TRUE);
      w.eq(UserEntity::getUserId, userId);
    });
  }

  public boolean mobileIsChange(String userId, String mobile) {

    UserEntity user = this.selectOne(w -> {
      w.select(UserEntity::getMobile);
      w.eq(UserEntity::getUserId, userId);
    });
    return !StringUtils.equals(user.getMobile(), mobile);
  }

  public void updatePassword(String userId, String newPwd) {

    String salt = RandomStringUtils.randomAlphabetic(16);
    String secret = passwordEncoder.encode(newPwd);
    this.updateByWhere(w -> {
      w.set(UserEntity::getPassword, secret);
      w.set(UserEntity::getSalt, salt);
      w.eq(UserEntity::getUserId, userId);
    });
  }

}
