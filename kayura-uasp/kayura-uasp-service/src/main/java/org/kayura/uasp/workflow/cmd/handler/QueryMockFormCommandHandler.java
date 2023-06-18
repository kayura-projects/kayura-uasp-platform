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
