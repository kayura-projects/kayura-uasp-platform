/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
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
import org.kayura.uasp.auth.cmd.QueryClientUserCommand;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.user.ClientUserQuery;
import org.kayura.uasp.user.ClientUserVo;
import org.kayura.utils.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class QueryClientUserCommandHandler implements CommandHandler<QueryClientUserCommand, HttpResult> {

  private final UserManager userManager;

  public QueryClientUserCommandHandler(UserManager userManager) {
    this.userManager = userManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryClientUserCommand command) {

    ClientUserQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    PageList<ClientUserVo> pageList = userManager.selectPage(w -> {
      w.of(query);
      if (query.getExpired() != null) {
        if (Boolean.TRUE.equals(query.getExpired())) {
          w.and(x -> x.isNotNull(UserEntity::getAccountExpire).gtEq(UserEntity::getAccountExpire, DateUtils.now()));
        } else {
          w.and(x -> x.isNull(UserEntity::getAccountExpire).or().ltEq(UserEntity::getAccountExpire, DateUtils.now()));
        }
      }
      w.eq(UserEntity::getUserType, UserTypes.CLIENT);
      w.isNull(UserEntity::getTenantId);
    }, pageClause).streamMap(entity ->
      ClientUserVo.create()
        .setClientId(entity.getUserId())
        .setClientNo(entity.getUserName())
        .setSecretKey(entity.getPassword())
        .setEnabled(entity.getEnabled())
        .setAccountExpire(entity.getAccountExpire())
        .setDisplayName(entity.getDisplayName())
        .setRemark(entity.getRemark())
    );
    return HttpResult.okBody(pageList);
  }

}
