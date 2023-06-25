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
import org.kayura.uasp.organize.IdentityPayload;
import org.kayura.uasp.organize.IdentityVo;
import org.kayura.uasp.organize.JobStatus;
import org.kayura.uasp.organize.cmd.CreateIdentityCommand;
import org.kayura.uasp.organize.entity.IdentityEntity;
import org.kayura.uasp.organize.entity.PositionEntity;
import org.kayura.uasp.organize.manage.IdentityManager;
import org.kayura.uasp.organize.manage.PositionManager;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class CreateIdentityCommandHandler implements CommandHandler<CreateIdentityCommand, HttpResult> {

  private final IdentityManager identityManager;
  private final PositionManager positionManager;
  private final ModelMapper modelMapper;

  public CreateIdentityCommandHandler(IdentityManager identityManager,
                                      PositionManager positionManager,
                                      ModelMapper modelMapper) {
    this.identityManager = identityManager;
    this.positionManager = positionManager;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public HttpResult execute(CreateIdentityCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdentityPayload payload = command.getPayload();

    if (StringUtils.isAllBlank(payload.getDepartId(), payload.getPositionId())) {
      return HttpResult.error("必需指定部门或者岗位。");
    }

    if (StringUtils.isBlank(payload.getDepartId())) {
      PositionEntity position = positionManager.selectOne(w -> {
        w.select(PositionEntity::getDepartId);
        w.eq(PositionEntity::getPositionId, payload.getPositionId());
      });
      if (position == null) {
        return HttpResult.error("指定的岗位不存在。");
      }
      payload.setDepartId(position.getDepartId());
    }

    payload.setStatus(Optional.ofNullable(payload.getStatus()).orElse(JobStatus.Active));

    IdentityEntity entity = IdentityEntity.create()
      .setIdentityId(identityManager.nextId())
      .setDepartId(payload.getDepartId())
      .setPositionId(payload.getPositionId())
      .setEmployeeId(payload.getEmployeeId())
      .setEnterDate(payload.getEnterDate())
      .setOverDate(payload.getOverDate())
      .setStatus(payload.getStatus())
      .setRemark(payload.getRemark());
    identityManager.insertOne(entity);

    IdentityVo model = modelMapper.map(entity, IdentityVo.class);
    return HttpResult.okBody(model);
  }

}
