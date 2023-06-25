/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

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
