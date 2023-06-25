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
