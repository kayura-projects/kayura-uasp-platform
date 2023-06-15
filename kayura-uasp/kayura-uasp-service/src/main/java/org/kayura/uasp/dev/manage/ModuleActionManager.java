package org.kayura.uasp.dev.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.type.CodeName;
import org.kayura.uasp.dev.entity.ModuleDefineEntity;
import org.kayura.uasp.dev.mapper.ModuleActionMapper;
import org.kayura.uasp.dev.entity.ModuleActionEntity;
import org.kayura.uasp.func.ActionTypes;
import org.kayura.uasp.func.ModulePayload;
import org.kayura.utils.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModuleActionManager extends CrudManagerImpl<ModuleActionMapper, ModuleActionEntity> {

  protected ModuleActionManager(ModuleActionMapper baseMapper) {
    super(baseMapper);
  }

  public void batchInsertActions(ModulePayload payload, ModuleDefineEntity entity) {

    List<CodeName> actions = payload.getActions();
    if (CollectionUtils.isNotEmpty(actions)) {
      List<ModuleActionEntity> collect = new ArrayList<>();
      for (int i = 0; i < actions.size(); i++) {
        ModuleActionEntity action = ModuleActionEntity.create()
          .setModuleId(entity.getModuleId())
          .setType(ActionTypes.Func)
          .setCode(actions.get(i).getCode())
          .setName(actions.get(i).getName())
          .setSort(i);
        collect.add(action);
      }
      this.insertBatch(collect);
    }
  }
}
