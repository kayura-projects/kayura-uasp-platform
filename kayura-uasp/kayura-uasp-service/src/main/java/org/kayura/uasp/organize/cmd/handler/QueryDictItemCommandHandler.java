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
