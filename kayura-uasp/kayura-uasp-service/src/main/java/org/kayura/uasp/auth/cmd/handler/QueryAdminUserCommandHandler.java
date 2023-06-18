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
