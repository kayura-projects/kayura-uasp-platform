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
