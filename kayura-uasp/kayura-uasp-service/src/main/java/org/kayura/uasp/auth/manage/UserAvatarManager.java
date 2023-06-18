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
import org.kayura.uasp.auth.entity.UserAvatarEntity;
import org.kayura.uasp.auth.mapper.UserAvatarMapper;
import org.kayura.uasp.user.UserAvatarVo;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserAvatarManager extends CrudManagerImpl<UserAvatarMapper, UserAvatarEntity> {

  protected UserAvatarManager(UserAvatarMapper baseMapper) {
    super(baseMapper);
  }

  public void writeHistory(String targetUseId, String avatarId, String loginUserId) {

    if (StringUtils.hasText(avatarId)) {
      // is exists
      UserAvatarEntity avatar = this.selectOne(w -> {
        w.eq(UserAvatarEntity::getUserId, targetUseId);
        w.eq(UserAvatarEntity::getPhotoId, avatarId);
      });
      if (avatar != null) {
        final UserAvatarEntity finalAvatar = avatar;
        this.updateByWhere(w -> {
          w.set(UserAvatarEntity::getUpdateTime, LocalDateTime.now());
          w.eq(UserAvatarEntity::getAvatarId, finalAvatar.getAvatarId());
        });
      } else {
        avatar = UserAvatarEntity.create()
          .setAvatarId(this.nextId())
          .setUserId(targetUseId)
          .setPhotoId(avatarId)
          .setUpdaterId(loginUserId)
          .setUpdateTime(LocalDateTime.now());
        this.insertOne(avatar);
      }
    }
  }

  public List<UserAvatarVo> queryHistory(String userId) {

    List<UserAvatarVo> collect = this.selectList(w -> {
      w.eq(UserAvatarEntity::getUserId, userId);
    }).stream().map(m ->
      UserAvatarVo.create().setPhotoId(m.getPhotoId()).setUpdateTime(m.getUpdateTime())
    ).collect(Collectors.toList());
    return collect;
  }

}
