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
