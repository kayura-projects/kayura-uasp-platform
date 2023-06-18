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
import org.kayura.uasp.basic.manage.DictItemManager;
import org.kayura.uasp.dict.DictItemQuery;
import org.kayura.uasp.dict.DictItemVo;
import org.kayura.uasp.organize.cmd.QueryDictItemCommand;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QueryDictItemCommandHandler implements CommandHandler<QueryDictItemCommand, HttpResult> {

  private final DictItemManager itemManager;
  private final ModelMapper modelMapper;

  public QueryDictItemCommandHandler(DictItemManager itemManager,
                                     ModelMapper modelMapper) {
    this.itemManager = itemManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryDictItemCommand command) {

    OutputTypes output = command.getOutput();
    DictItemQuery query = command.getQuery();

    if (OutputTypes.TREE.equals(output)) {

      List<DictItemVo> collect = itemManager.selectList(w -> {
        w.of(query);
      }).stream().map(m -> modelMapper.map(m, DictItemVo.class)).collect(Collectors.toList());
      List<DictItemVo> rootItems = collect.stream()
        .filter(x -> StringUtils.isBlank(x.getParentId())).collect(Collectors.toList());
      makeChildren(rootItems, collect);
      return HttpResult.okBody(rootItems);

    } else {

      PageClause pageClause = command.getPageClause();
      PageList<DictItemVo> pageList = itemManager.selectPage(w -> {
        w.of(query);
      }, pageClause).streamMap(m -> modelMapper.map(m, DictItemVo.class));
      return HttpResult.okBody(pageList);
    }
  }

  private void makeChildren(List<DictItemVo> items, List<DictItemVo> allItems) {

    for (DictItemVo item : items) {
      List<DictItemVo> collect = allItems.stream()
        .filter(x -> item.getItemId().equals(x.getParentId())).collect(Collectors.toList());
      if (!collect.isEmpty()) {
        item.setChildren(collect);
        makeChildren(collect, allItems);
      }
    }
  }
}
