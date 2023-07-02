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

package org.kayura.uasp.basic.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.type.CodeName;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.basic.cmd.QueryModuleCommand;
import org.kayura.uasp.dev.entity.ModuleActionEntity;
import org.kayura.uasp.dev.manage.ModuleActionManager;
import org.kayura.uasp.dev.manage.ModuleDefineManager;
import org.kayura.uasp.func.ModuleQuery;
import org.kayura.uasp.func.ModuleVo;
import org.kayura.utils.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QueryModuleCommandHandler implements CommandHandler<QueryModuleCommand, HttpResult> {

  private final ModuleDefineManager defineManager;
  private final ModelMapper modelMapper;
  private final ModuleActionManager actionManager;

  public QueryModuleCommandHandler(ModuleDefineManager defineManager,
                                   ModelMapper modelMapper,
                                   ModuleActionManager actionManager) {
    this.defineManager = defineManager;
    this.modelMapper = modelMapper;
    this.actionManager = actionManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryModuleCommand command) {

    ModuleQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    PageList<ModuleVo> pageList = defineManager.selectPage(w -> {
      w.of(query);
    }, pageClause).streamMap(m -> modelMapper.map(m, ModuleVo.class));

    // 加载操作项
    List<ModuleVo> rows = pageList.getRows();
    if (CollectionUtils.isNotEmpty(rows)) {
      List<String> moduleIds = rows.stream().map(ModuleVo::getModuleId).toList();
      List<ModuleActionEntity> entities = actionManager.selectList(w -> {
        w.in(ModuleActionEntity::getModuleId, moduleIds);
      });
      for (ModuleVo vo : rows) {
        List<CodeName> actions = entities.stream()
          .filter(x -> x.getModuleId().equalsIgnoreCase(vo.getModuleId()))
          .map(m -> CodeName.create().setCode(m.getCode()).setName(m.getName()))
          .toList();
        vo.setActions(actions);
      }
    }

    return HttpResult.okBody(pageList);
  }

}
