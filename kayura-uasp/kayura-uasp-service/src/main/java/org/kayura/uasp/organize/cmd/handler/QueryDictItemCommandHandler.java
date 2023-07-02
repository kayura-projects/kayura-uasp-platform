/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.basic.entity.DictItemEntity;
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

    LoginUser loginUser = command.getLoginUser();
    OutputTypes output = command.getOutput();
    DictItemQuery query = command.getQuery();

    if (OutputTypes.TREE.equals(output)) {

      List<DictItemVo> collect = itemManager.selectList(w -> {
        w.of(query);
        if (loginUser.hasRootOrAdmin()) {
          w.isNull(DictItemEntity::getTenantId);
        } else if (loginUser.hasTenantUser()) {
          w.and(w1 -> w1.isNull(DictItemEntity::getTenantId).or().eq(DictItemEntity::getTenantId, loginUser.getTenantId()));
        }
      }).stream().map(m -> modelMapper.map(m, DictItemVo.class)).toList();
      List<DictItemVo> rootItems = collect.stream()
        .filter(x -> StringUtils.isBlank(x.getParentId())).toList();
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
        .filter(x -> item.getItemId().equals(x.getParentId())).toList();
      if (!collect.isEmpty()) {
        item.setChildren(collect);
        makeChildren(collect, allItems);
      }
    }
  }
}
