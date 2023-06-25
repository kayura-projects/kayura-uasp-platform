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
