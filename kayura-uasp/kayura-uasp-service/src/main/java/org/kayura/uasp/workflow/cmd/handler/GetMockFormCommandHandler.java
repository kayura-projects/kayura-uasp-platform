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
import org.kayura.type.HttpResult;
import org.kayura.uasp.mockform.MockFormVo;
import org.kayura.uasp.workflow.cmd.GetMockFormCommand;
import org.kayura.uasp.workflow.entity.MockFormEntity;
import org.kayura.uasp.workflow.manage.MockFormManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GetMockFormCommandHandler implements CommandHandler<GetMockFormCommand, HttpResult> {

  private final MockFormManager mockFormManager;
  private final ModelMapper modelMapper;

  public GetMockFormCommandHandler(MockFormManager mockFormManager,
                                   ModelMapper modelMapper) {
    this.mockFormManager = mockFormManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(GetMockFormCommand command) {

    String mockId = command.getMockId();

    MockFormEntity entity = mockFormManager.selectById(mockId);
    if (entity == null) {
      return HttpResult.error("要获取的记录不存在。");
    }
    MockFormVo vo = modelMapper.map(entity, MockFormVo.class);
    return HttpResult.okBody(vo);
  }

}
