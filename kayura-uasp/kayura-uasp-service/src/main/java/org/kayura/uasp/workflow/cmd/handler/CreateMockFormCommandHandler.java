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
