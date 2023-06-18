/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
