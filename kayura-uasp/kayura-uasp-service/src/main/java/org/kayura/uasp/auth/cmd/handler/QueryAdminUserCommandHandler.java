package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.type.UserTypes;
import org.kayura.uasp.auth.cmd.QueryAdminUserCommand;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.user.AdminUserQuery;
import org.kayura.uasp.user.AdminUserVo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class QueryAdminUserCommandHandler implements CommandHandler<QueryAdminUserCommand, HttpResult> {

  private final UserManager userManager;
  private final ModelMapper modelMapper;

  public QueryAdminUserCommandHandler(UserManager userManager,
                                      ModelMapper modelMapper) {
    this.userManager = userManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryAdminUserCommand command) {

    AdminUserQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    PageList<AdminUserVo> pageList = userManager.selectPage(w -> {
      w.of(query);
      w.eq(UserEntity::getUserType, UserTypes.ADMIN);
      w.isNull(UserEntity::getTenantId);
    }, pageClause).streamMap(m -> modelMapper.map(m, AdminUserVo.class));
    return HttpResult.okBody(pageList);
  }

}
