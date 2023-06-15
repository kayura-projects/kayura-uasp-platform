package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.auth.cmd.QueryTenantCommand;
import org.kayura.uasp.auth.manage.TenantManager;
import org.kayura.uasp.tenant.TenantQuery;
import org.kayura.uasp.tenant.TenantVo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class QueryTenantCommandHandler implements CommandHandler<QueryTenantCommand, HttpResult> {

  private final TenantManager tenantManager;
  private final ModelMapper modelMapper;

  public QueryTenantCommandHandler(TenantManager tenantManager,
                                   ModelMapper modelMapper) {
    this.tenantManager = tenantManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryTenantCommand command) {

    TenantQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    PageList<TenantVo> pageList = tenantManager.selectPage(w -> {
      w.of(query);
    }, pageClause).streamMap(m -> modelMapper.map(m, TenantVo.class));
    return HttpResult.okBody(pageList);
  }

}
