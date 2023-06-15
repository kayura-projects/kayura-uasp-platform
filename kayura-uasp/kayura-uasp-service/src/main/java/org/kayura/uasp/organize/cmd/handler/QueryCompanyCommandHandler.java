package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.company.CompanyQuery;
import org.kayura.uasp.company.CompanyVo;
import org.kayura.uasp.organize.cmd.QueryCompanyCommand;
import org.kayura.uasp.organize.manage.CompanyManager;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class QueryCompanyCommandHandler implements CommandHandler<QueryCompanyCommand, HttpResult> {

  private final CompanyManager companyManager;
  private final ModelMapper modelMapper;

  public QueryCompanyCommandHandler(CompanyManager companyManager,
                                    ModelMapper modelMapper) {
    this.companyManager = companyManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryCompanyCommand command) {

    LoginUser loginUser = command.getLoginUser();
    CompanyQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    if (StringUtils.hasText(loginUser.getTenantId())) {
      query.setTenantId(loginUser.getTenantId());
    }

    PageList<CompanyVo> pageList = companyManager.selectPage(w -> {
      w.of(query);
    }, pageClause).streamMap(m -> modelMapper.map(m, CompanyVo.class));
    return HttpResult.okBody(pageList);
  }

}
