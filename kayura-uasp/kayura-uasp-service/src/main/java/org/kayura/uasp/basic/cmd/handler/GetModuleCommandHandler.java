/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
