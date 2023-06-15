/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
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
