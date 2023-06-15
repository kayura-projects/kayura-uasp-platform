package org.kayura.uasp.ops.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.applibrary.AppStoreQuery;
import org.kayura.uasp.applibrary.AppStoreVo;
import org.kayura.uasp.ops.cmd.QueryAppStoreCommand;
import org.kayura.uasp.ops.manage.AppStoreManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class QueryAppStoreCommandHandler implements CommandHandler<QueryAppStoreCommand, HttpResult> {

  private final ModelMapper modelMapper;
  private final AppStoreManager appStoreManager;

  public QueryAppStoreCommandHandler(ModelMapper modelMapper,
                                     AppStoreManager appStoreManager) {
    this.modelMapper = modelMapper;
    this.appStoreManager = appStoreManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryAppStoreCommand command) {

    LoginUser loginUser = command.getLoginUser();
    PageClause pageClause = command.getPageClause();
    AppStoreQuery query = command.getQuery();

    PageList<AppStoreVo> pageList = appStoreManager.selectPage(w -> {
      w.of(query);
    }, pageClause).streamMap(m -> modelMapper.map(m, AppStoreVo.class));
    return HttpResult.okBody(pageList);
  }
}
