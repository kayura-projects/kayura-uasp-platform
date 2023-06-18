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
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.basic.entity.DictDefineEntity;
import org.kayura.uasp.basic.manage.DictDefineManager;
import org.kayura.uasp.dict.DictDefineQuery;
import org.kayura.uasp.dict.DictDefineVo;
import org.kayura.uasp.organize.cmd.QueryDictDefineCommand;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class QueryDictDefineCommandHandler implements CommandHandler<QueryDictDefineCommand, HttpResult> {

  private final DictDefineManager defineManager;
  private final ModelMapper modelMapper;

  public QueryDictDefineCommandHandler(DictDefineManager defineManager,
                                       ModelMapper modelMapper) {
    this.defineManager = defineManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryDictDefineCommand command) {

    DictDefineQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    PageList<DictDefineVo> pageList = defineManager.selectPage(w -> {
      if (DictDefineQuery.APP.equalsIgnoreCase(query.getType())) {
        w.eq(DictDefineEntity::getAppId, query.getAppId());
        w.isNull(DictDefineEntity::getParentId);
      } else if (DictDefineQuery.Category.equalsIgnoreCase(query.getType())) {
        w.eq(DictDefineEntity::getParentId, query.getParentId());
      }
      w.of(query);
    }, pageClause).streamMap(m -> modelMapper.map(m, DictDefineVo.class));

    return HttpResult.okBody(pageList);
  }

}
