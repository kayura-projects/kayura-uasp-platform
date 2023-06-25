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
