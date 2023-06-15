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
import org.kayura.uasp.form.FormDefineQuery;
import org.kayura.uasp.form.FormDefineVo;
import org.kayura.uasp.workflow.cmd.QueryFormDefineCommand;
import org.kayura.uasp.workflow.manage.FormDefineManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class QueryFormDefineCommandHandler implements CommandHandler<QueryFormDefineCommand, HttpResult> {

  private final FormDefineManager defineManager;
  private final ModelMapper modelMapper;

  public QueryFormDefineCommandHandler(FormDefineManager defineManager,
                                       ModelMapper modelMapper) {
    this.defineManager = defineManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryFormDefineCommand command) {

    LoginUser loginUser = command.getLoginUser();
    FormDefineQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    PageList<FormDefineVo> pageList = defineManager
      .selectPage(w -> w.of(query), pageClause)
      .streamMap(m -> modelMapper.map(m, FormDefineVo.class));

    return HttpResult.okBody(pageList);
  }

}
