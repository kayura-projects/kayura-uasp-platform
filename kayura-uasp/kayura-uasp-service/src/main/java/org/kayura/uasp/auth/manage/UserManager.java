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
