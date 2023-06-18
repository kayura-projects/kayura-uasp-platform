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
