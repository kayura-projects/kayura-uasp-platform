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

package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.except.ExceptUtils;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.organize.PositionPayload;
import org.kayura.uasp.organize.PositionVo;
import org.kayura.uasp.organize.cmd.CreatePositionCommand;
import org.kayura.uasp.organize.entity.DepartEntity;
import org.kayura.uasp.organize.entity.PositionEntity;
import org.kayura.uasp.organize.manage.DepartManager;
import org.kayura.uasp.organize.manage.PositionManager;
import org.kayura.utils.DateUtils;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreatePositionCommandHandler implements CommandHandler<CreatePositionCommand, HttpResult> {

  private final DepartManager departManager;
  private final PositionManager positionManager;
  private final ModelMapper modelMapper;

  public CreatePositionCommandHandler(DepartManager departManager,
                                      PositionManager positionManager,
                                      ModelMapper modelMapper) {
    this.departManager = departManager;
    this.positionManager = positionManager;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public HttpResult execute(CreatePositionCommand command) {

    LoginUser loginUser = command.getLoginUser();
    PositionPayload payload = command.getPayload();

    if (departManager.selectCount(w -> w.eq(DepartEntity::getDepartId, payload.getDepartId())) == 0) {
      ExceptUtils.business("指定的部门ID不存在。");
    }

    if (StringUtils.hasText(payload.getCode()) && positionManager.selectCount(w -> {
      w.eq(PositionEntity::getDepartId, payload.getDepartId());
      w.eq(PositionEntity::getCode, payload.getCode());
    }) > 0) {
      ExceptUtils.business("编号已经存在，不允许重复。");
    }

    PositionEntity entity = PositionEntity.create();
    entity.setPositionId(positionManager.nextId());
    entity.setDepartId(payload.getDepartId());
    entity.setCode(payload.getCode());
    entity.setName(payload.getName());
    entity.setLevel(payload.getLevel() != null ? payload.getLevel() : 0);
    entity.setRemark(payload.getRemark());
    entity.setCreatorId(loginUser.getUserId());
    entity.setCreateTime(DateUtils.now());
    entity.setSort(payload.getSort() != null ? payload.getSort() : 0);
    entity.setStatus(payload.getStatus());
    positionManager.insertOne(entity);

    PositionVo model = modelMapper.map(entity, PositionVo.class);
    return HttpResult.okBody(model);
  }
}
