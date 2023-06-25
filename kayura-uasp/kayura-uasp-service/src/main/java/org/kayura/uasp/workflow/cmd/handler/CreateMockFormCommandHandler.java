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
package org.kayura.uasp.workflow.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.ApproveStatus;
import org.kayura.type.HttpResult;
import org.kayura.uasp.mockform.MockFormPayload;
import org.kayura.uasp.mockform.MockFormVo;
import org.kayura.uasp.workflow.cmd.CreateMockFormCommand;
import org.kayura.uasp.workflow.entity.MockFormEntity;
import org.kayura.uasp.workflow.manage.MockFormManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class CreateMockFormCommandHandler implements CommandHandler<CreateMockFormCommand, HttpResult> {

  private final MockFormManager mockFormManager;
  private final ModelMapper modelMapper;

  public CreateMockFormCommandHandler(MockFormManager mockFormManager,
                                      ModelMapper modelMapper) {
    this.mockFormManager = mockFormManager;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public HttpResult execute(CreateMockFormCommand command) {

    LoginUser loginUser = command.getLoginUser();
    MockFormPayload payload = command.getPayload();

    if (mockFormManager.selectCount(w ->
      w.eq(MockFormEntity::getCode, payload.getCode())
    ) > 0) {
      return HttpResult.error("编号 " + payload.getCode() + " 已经被占用。");
    }

    MockFormEntity entity = MockFormEntity.create()
      .setMockId(mockFormManager.nextId())
      .setUsage(payload.getUsage())
      .setTenantId(payload.getTenantId())
      .setFormId(payload.getFormId())
      .setCode(payload.getCode())
      .setName(payload.getName())
      .setExtend(payload.getExtend())
      .setStatus(ApproveStatus.Draft)
      .setCreatorId(loginUser.getUserId())
      .setCreateTime(LocalDateTime.now())
      .setRemark(payload.getRemark());
    mockFormManager.insertOne(entity);

    MockFormVo model = modelMapper.map(entity, MockFormVo.class);
    return HttpResult.okBody(model);
  }

}
