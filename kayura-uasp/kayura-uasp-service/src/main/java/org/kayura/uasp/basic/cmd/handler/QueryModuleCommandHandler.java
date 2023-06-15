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
