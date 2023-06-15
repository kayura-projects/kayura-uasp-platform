package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.organize.OrganizeQuery;
import org.kayura.uasp.organize.OrganizeVo;
import org.kayura.uasp.organize.cmd.QueryOrganizeCommand;
import org.kayura.uasp.organize.manage.OrganizeManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class QueryOrganizeCommandHandler implements CommandHandler<QueryOrganizeCommand, HttpResult> {

  private final OrganizeManager organizeManager;
  private final ModelMapper modelMapper;

  public QueryOrganizeCommandHandler(OrganizeManager organizeManager,
                                     ModelMapper modelMapper) {
    this.organizeManager = organizeManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryOrganizeCommand command) {

    LoginUser loginUser = command.getLoginUser();
    OrganizeQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    PageList<OrganizeVo> pageList = organizeManager.selectPage(w -> w.of(query), pageClause)
      .streamMap(m -> modelMapper.map(m, OrganizeVo.class));
    return HttpResult.okBody(pageList);
  }
}
