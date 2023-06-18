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
      List<String> moduleIds = rows.stream().map(ModuleVo::getModuleId).collect(Collectors.toList());
      List<ModuleActionEntity> entities = actionManager.selectList(w -> {
        w.in(ModuleActionEntity::getModuleId, moduleIds);
      });
      for (ModuleVo vo : rows) {
        List<CodeName> actions = entities.stream()
          .filter(x -> x.getModuleId().equalsIgnoreCase(vo.getModuleId()))
          .map(m -> CodeName.create().setCode(m.getCode()).setName(m.getName()))
          .collect(Collectors.toList());
        vo.setActions(actions);
      }
    }

    return HttpResult.okBody(pageList);
  }

}
