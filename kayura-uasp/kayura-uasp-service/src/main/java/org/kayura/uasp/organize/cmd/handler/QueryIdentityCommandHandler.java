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

package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.OrderByClause;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.organize.IdentityQuery;
import org.kayura.uasp.organize.IdentityVo;
import org.kayura.uasp.organize.cmd.QueryIdentityCommand;
import org.kayura.uasp.organize.manage.IdentityManager;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class QueryIdentityCommandHandler implements CommandHandler<QueryIdentityCommand, HttpResult> {

  private final ModelMapper modelMapper;
  private final IdentityManager identityManager;

  public QueryIdentityCommandHandler(ModelMapper modelMapper,
                                     IdentityManager identityManager) {
    this.modelMapper = modelMapper;
    this.identityManager = identityManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryIdentityCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdentityQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();
    String employeeId = Optional.ofNullable(command.getEmployeeId()).orElse(query.getEmployeeId());

    if (StringUtils.isBlank(employeeId)) {
      return HttpResult.error("必需要指定员工ID条件。");
    }

    if (pageClause != null) {
      PageList<IdentityVo> pageList = identityManager.selectPage(w -> {
        w.of(query);
      }, pageClause).streamMap(m -> modelMapper.map(m, IdentityVo.class));
      return HttpResult.okBody(pageList);
    } else {
      OrderByClause orderByClause = command.getOrderByClause();
      List<IdentityVo> list = identityManager.selectList(w -> {
        w.of(query);
        w.orderByOf(orderByClause);
      }).stream().map(m -> modelMapper.map(m, IdentityVo.class)).toList();
      return HttpResult.okBody(list);
    }
  }

}
