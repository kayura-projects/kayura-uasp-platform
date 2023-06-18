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
package org.kayura.uasp.workflow.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.form.FormDefineQuery;
import org.kayura.uasp.form.FormDefineVo;
import org.kayura.uasp.workflow.cmd.QueryFormDefineCommand;
import org.kayura.uasp.workflow.manage.FormDefineManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class QueryFormDefineCommandHandler implements CommandHandler<QueryFormDefineCommand, HttpResult> {

  private final FormDefineManager defineManager;
  private final ModelMapper modelMapper;

  public QueryFormDefineCommandHandler(FormDefineManager defineManager,
                                       ModelMapper modelMapper) {
    this.defineManager = defineManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryFormDefineCommand command) {

    LoginUser loginUser = command.getLoginUser();
    FormDefineQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    PageList<FormDefineVo> pageList = defineManager
      .selectPage(w -> w.of(query), pageClause)
      .streamMap(m -> modelMapper.map(m, FormDefineVo.class));

    return HttpResult.okBody(pageList);
  }

}
