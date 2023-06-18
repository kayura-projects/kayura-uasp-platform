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
import org.kayura.uasp.workflow.FormFlowQuery;
import org.kayura.uasp.workflow.FormFlowVo;
import org.kayura.uasp.workflow.cmd.QueryFormFlowCommand;
import org.kayura.uasp.workflow.manage.FormFlowManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class QueryFormFlowCommandHandler implements CommandHandler<QueryFormFlowCommand, HttpResult> {

  private final FormFlowManager flowManager;
  private final ModelMapper modelMapper;

  public QueryFormFlowCommandHandler(FormFlowManager flowManager,
                                     ModelMapper modelMapper) {
    this.flowManager = flowManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryFormFlowCommand command) {

    LoginUser loginUser = command.getLoginUser();
    FormFlowQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    PageList<FormFlowVo> pageList = flowManager
      .selectPage(w -> w.of(query), pageClause)
      .streamMap(m -> modelMapper.map(m, FormFlowVo.class));
    return HttpResult.okBody(pageList);
  }
}
