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
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.basic.cmd.UpdateModuleCommand;
import org.kayura.uasp.dev.entity.ModuleActionEntity;
import org.kayura.uasp.dev.entity.ModuleDefineEntity;
import org.kayura.uasp.dev.manage.ModuleActionManager;
import org.kayura.uasp.dev.manage.ModuleDefineManager;
import org.kayura.uasp.func.ModulePayload;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateModuleCommandHandler implements CommandHandler<UpdateModuleCommand, HttpResult> {

  private final ModuleDefineManager defineManager;
  private final ModuleActionManager actionManager;

  public UpdateModuleCommandHandler(ModuleDefineManager defineManager,
                                    ModuleActionManager actionManager) {
    this.defineManager = defineManager;
    this.actionManager = actionManager;
  }

  @Transactional
  public HttpResult execute(UpdateModuleCommand command) {

    LoginUser loginUser = command.getLoginUser();
    String moduleId = command.getModuleId();
    ModulePayload payload = command.getPayload();

    ModuleDefineEntity entity = defineManager.selectById(moduleId);
    if (entity == null) {
      return HttpResult.error("更新的模块不存在。");
    }

    if (StringUtils.hasText(payload.getCode()) && !payload.getCode().equalsIgnoreCase(entity.getCode()) &&
      defineManager.selectCount(w -> {
        w.eq(ModuleDefineEntity::getCode, payload.getCode());
        w.notEq(ModuleDefineEntity::getAppId, moduleId);
      }) > 0) {
      return HttpResult.error("模块编码已经被占用。");
    }

    entity.setCode(payload.getCode());
    entity.setName(payload.getName());
    entity.setIcon(payload.getIcon());
    entity.setType(payload.getType());
    entity.setUrl(payload.getUrl());
    entity.setTarget(payload.getTarget());
    entity.setSort(payload.getSort());
    entity.setUsage(payload.getUsage());
    entity.setStatus(payload.getStatus());
    entity.setRemark(payload.getRemark());
    defineManager.updateById(moduleId, entity);

    // 保存操作项
    actionManager.deleteByWhere(w -> w.eq(ModuleActionEntity::getModuleId, moduleId));
    actionManager.batchInsertActions(payload, entity);

    return HttpResult.ok();
  }

}
