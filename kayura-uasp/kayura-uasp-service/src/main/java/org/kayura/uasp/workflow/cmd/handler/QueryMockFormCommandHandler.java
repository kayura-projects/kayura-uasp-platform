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
import org.kayura.uasp.mockform.MockFormQuery;
import org.kayura.uasp.mockform.MockFormVo;
import org.kayura.uasp.workflow.cmd.QueryMockFormCommand;
import org.kayura.uasp.workflow.manage.MockFormManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class QueryMockFormCommandHandler implements CommandHandler<QueryMockFormCommand, HttpResult> {

  private final MockFormManager mockFormManager;
  private final ModelMapper modelMapper;

  public QueryMockFormCommandHandler(MockFormManager mockFormManager,
                                     ModelMapper modelMapper) {
    this.mockFormManager = mockFormManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryMockFormCommand command) {

    LoginUser loginUser = command.getLoginUser();
    MockFormQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    PageList<MockFormVo> pageList = mockFormManager.selectPage(w -> w.of(query), pageClause)
      .streamMap(m -> modelMapper.map(m, MockFormVo.class));
    return HttpResult.okBody(pageList);
  }

}
