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
