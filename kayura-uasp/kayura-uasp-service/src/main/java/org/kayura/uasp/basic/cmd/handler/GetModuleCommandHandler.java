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
import org.kayura.uasp.basic.cmd.GetModuleCommand;
import org.kayura.uasp.dev.entity.ModuleActionEntity;
import org.kayura.uasp.dev.entity.ModuleDefineEntity;
import org.kayura.uasp.dev.manage.ModuleActionManager;
import org.kayura.uasp.dev.manage.ModuleDefineManager;
import org.kayura.uasp.func.ModuleVo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class GetModuleCommandHandler implements CommandHandler<GetModuleCommand, HttpResult> {

  private final ModelMapper modelMapper;
  private final ModuleDefineManager defineManager;
  private final ModuleActionManager actionManager;

  public GetModuleCommandHandler(ModelMapper modelMapper,
                                 ModuleDefineManager defineManager,
                                 ModuleActionManager actionManager) {
    this.modelMapper = modelMapper;
    this.defineManager = defineManager;
    this.actionManager = actionManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(GetModuleCommand command) {

    String moduleId = command.getModuleId();

    ModuleDefineEntity entity = defineManager.selectById(moduleId);
    if (entity == null) {
      return HttpResult.error("查找的模块记录不存在。");
    }
    ModuleVo model = modelMapper.map(entity, ModuleVo.class);
    List<CodeName> actions = actionManager
      .selectList(w -> w.eq(ModuleActionEntity::getModuleId, moduleId))
      .stream().map(m -> CodeName.create().setCode(m.getCode()).setName(m.getName()))
      .toList();
    model.setActions(actions);
    return HttpResult.okBody(model);
  }

}
