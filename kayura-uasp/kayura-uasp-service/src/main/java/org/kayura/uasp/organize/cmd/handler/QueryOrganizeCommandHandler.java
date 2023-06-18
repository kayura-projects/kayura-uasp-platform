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
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.organize.OrganizeQuery;
import org.kayura.uasp.organize.OrganizeVo;
import org.kayura.uasp.organize.cmd.QueryOrganizeCommand;
import org.kayura.uasp.organize.manage.OrganizeManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class QueryOrganizeCommandHandler implements CommandHandler<QueryOrganizeCommand, HttpResult> {

  private final OrganizeManager organizeManager;
  private final ModelMapper modelMapper;

  public QueryOrganizeCommandHandler(OrganizeManager organizeManager,
                                     ModelMapper modelMapper) {
    this.organizeManager = organizeManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryOrganizeCommand command) {

    LoginUser loginUser = command.getLoginUser();
    OrganizeQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    PageList<OrganizeVo> pageList = organizeManager.selectPage(w -> w.of(query), pageClause)
      .streamMap(m -> modelMapper.map(m, OrganizeVo.class));
    return HttpResult.okBody(pageList);
  }
}
