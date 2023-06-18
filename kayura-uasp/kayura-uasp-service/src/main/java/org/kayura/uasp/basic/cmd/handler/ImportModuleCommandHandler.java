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
import org.kayura.type.HttpResult;
import org.kayura.uasp.basic.cmd.ImportModuleCommand;
import org.kayura.uasp.dev.entity.ModuleActionEntity;
import org.kayura.uasp.dev.entity.ModuleDefineEntity;
import org.kayura.uasp.dev.manage.ModuleActionManager;
import org.kayura.uasp.dev.manage.ModuleDefineManager;
import org.kayura.utils.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ImportModuleCommandHandler implements CommandHandler<ImportModuleCommand, HttpResult> {

  private final ModuleDefineManager defineManager;
  private final ModuleActionManager actionManager;

  public ImportModuleCommandHandler(ModuleDefineManager defineManager, ModuleActionManager actionManager) {
    this.defineManager = defineManager;
    this.actionManager = actionManager;
  }

  @Transactional
  public HttpResult execute(ImportModuleCommand command) {

    String targetAppId = command.getTargetAppId();
    String targetParentId = command.getTargetParentId();
    List<String> sourceModuleIds = command.getSourceModuleIds();

    if (StringUtils.isBlank(targetAppId)) {
      ModuleDefineEntity entity = this.defineManager.selectById(targetParentId);
      if (entity == null) {
        return HttpResult.error("指定的 ParentId 不存在。");
      }
      targetAppId = entity.getAppId();
    }

    final String finalAppId = targetAppId;
    List<ModuleDefineEntity> targetModules = this.defineManager.selectList(w -> {
      w.eq(ModuleDefineEntity::getAppId, finalAppId);
    });
    List<ModuleDefineEntity> sourceModules = this.defineManager.selectList(w -> {
      w.in(ModuleDefineEntity::getModuleId, sourceModuleIds);
    });
    List<ModuleActionEntity> sourceActions = this.actionManager.selectList(w -> {
      w.in(ModuleActionEntity::getModuleId, sourceModuleIds);
    });

    for (ModuleDefineEntity module : sourceModules) {

      final String oldId = module.getModuleId();
      final String newId = this.defineManager.nextId();

      // define
      module.setModuleId(newId);
      module.setAppId(targetAppId);
      module.setParentId(targetParentId);
      if (targetModules.stream().anyMatch(x -> module.getCode().equalsIgnoreCase(x.getCode()))) {
        module.setCode(module.getCode() + "_" + RandomStringUtils.randomAlphabetic(8));
      }
      this.defineManager.insertOne(module);

      // actions
      Set<ModuleActionEntity> actions = sourceActions.stream()
        .filter(x -> oldId.equalsIgnoreCase(x.getModuleId()))
        .map(m -> m.setModuleId(newId))
        .collect(Collectors.toSet());
      this.actionManager.insertBatch(actions);
    }

    return HttpResult.ok();
  }

}
