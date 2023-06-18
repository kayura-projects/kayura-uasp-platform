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
