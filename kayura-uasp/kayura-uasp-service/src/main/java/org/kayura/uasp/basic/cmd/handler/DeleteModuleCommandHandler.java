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
import org.kayura.uasp.basic.cmd.DeleteModuleCommand;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.dev.entity.ModuleDefineEntity;
import org.kayura.uasp.dev.manage.ModuleDefineManager;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DeleteModuleCommandHandler implements CommandHandler<DeleteModuleCommand, HttpResult> {

  private final ModuleDefineManager defineManager;

  public DeleteModuleCommandHandler(ModuleDefineManager defineManager) {
    this.defineManager = defineManager;
  }

  @Transactional
  public HttpResult execute(DeleteModuleCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdPayload payload = Optional.ofNullable(command.getPayload()).orElse(IdPayload.create());
    String moduleId = Optional.ofNullable(command.getModuleId()).orElse(payload.getId());


    if (CollectionUtils.isNotEmpty(payload.getIds())) {
      if (defineManager.selectCount(w -> {
        w.in(ModuleDefineEntity::getParentId, payload.getIds());
      }) > 0) {
        return HttpResult.error("存在下级模块，不可删除。");
      }
      defineManager.deleteByIds(payload.getIds());
    } else {
      if (StringUtils.hasText(moduleId)) {
        if (defineManager.selectCount(w -> {
          w.eq(ModuleDefineEntity::getParentId, moduleId);
        }) > 0) {
          return HttpResult.error("存在下级模块，不可删除。");
        }
        defineManager.deleteById(moduleId);
      }
    }

    return HttpResult.ok();
  }

}
