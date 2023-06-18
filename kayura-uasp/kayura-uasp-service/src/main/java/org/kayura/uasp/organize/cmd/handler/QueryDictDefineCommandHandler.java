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
