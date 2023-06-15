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
