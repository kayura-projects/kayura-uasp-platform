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

package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.organize.PositionPayload;
import org.kayura.uasp.organize.cmd.UpdatePositionCommand;
import org.kayura.uasp.organize.entity.PositionEntity;
import org.kayura.uasp.organize.manage.PositionManager;
import org.kayura.utils.DateUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdatePositionCommandHandler implements CommandHandler<UpdatePositionCommand, HttpResult> {

  private final PositionManager positionManager;

  public UpdatePositionCommandHandler(PositionManager positionManager) {
    this.positionManager = positionManager;
  }

  @Transactional
  public HttpResult execute(UpdatePositionCommand command) {

    LoginUser loginUser = command.getLoginUser();
    PositionPayload payload = command.getPayload();
    String positionId = command.getPositionId();

    PositionEntity entity = positionManager.selectById(positionId);
    if (entity == null) {
      return HttpResult.error("更新的数据不存在。");
    }

    if (StringUtils.hasText(payload.getCode()) &&
      !payload.getCode().equalsIgnoreCase(entity.getCode()) &&
      positionManager.selectCount(w -> {
        w.eq(PositionEntity::getCode, payload.getCode());
        w.eq(PositionEntity::getDepartId, entity.getDepartId());
        w.notEq(PositionEntity::getPositionId, positionId);
      }) > 0) {
      return HttpResult.error("编号已经存在。");
    }

    entity.setCode(payload.getCode());
    entity.setName(payload.getName());
    entity.setSort(payload.getSort());
    entity.setStatus(payload.getStatus());
    entity.setRemark(payload.getRemark());
    entity.setUpdaterId(loginUser.getUserId());
    entity.setUpdateTime(DateUtils.now());
    positionManager.updateById(positionId, entity);

    return HttpResult.ok();
  }

}
