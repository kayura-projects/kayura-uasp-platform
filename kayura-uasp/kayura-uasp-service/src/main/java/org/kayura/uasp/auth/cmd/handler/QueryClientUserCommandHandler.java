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
