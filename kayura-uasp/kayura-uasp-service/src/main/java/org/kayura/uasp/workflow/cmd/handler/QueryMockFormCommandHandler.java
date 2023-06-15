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
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.mockform.MockFormQuery;
import org.kayura.uasp.mockform.MockFormVo;
import org.kayura.uasp.workflow.cmd.QueryMockFormCommand;
import org.kayura.uasp.workflow.manage.MockFormManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class QueryMockFormCommandHandler implements CommandHandler<QueryMockFormCommand, HttpResult> {

  private final MockFormManager mockFormManager;
  private final ModelMapper modelMapper;

  public QueryMockFormCommandHandler(MockFormManager mockFormManager,
                                     ModelMapper modelMapper) {
    this.mockFormManager = mockFormManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryMockFormCommand command) {

    LoginUser loginUser = command.getLoginUser();
    MockFormQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    PageList<MockFormVo> pageList = mockFormManager.selectPage(w -> w.of(query), pageClause)
      .streamMap(m -> modelMapper.map(m, MockFormVo.class));
    return HttpResult.okBody(pageList);
  }

}
