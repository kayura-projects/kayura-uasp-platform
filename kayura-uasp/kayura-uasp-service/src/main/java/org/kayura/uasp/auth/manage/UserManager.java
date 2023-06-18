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
