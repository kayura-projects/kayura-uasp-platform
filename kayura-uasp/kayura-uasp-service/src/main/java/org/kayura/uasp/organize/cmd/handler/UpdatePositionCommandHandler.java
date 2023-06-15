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
