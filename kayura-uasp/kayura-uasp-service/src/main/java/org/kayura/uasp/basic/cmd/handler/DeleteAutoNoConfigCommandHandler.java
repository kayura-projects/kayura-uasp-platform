/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.basic.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.basic.cmd.DeleteAutoNoConfigCommand;
import org.kayura.uasp.basic.entity.AutoNoConfigEntity;
import org.kayura.uasp.basic.manage.AutoNoConfigManager;
import org.kayura.uasp.basic.manage.AutoNoDefineManager;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.utils.UaspConstants;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class DeleteAutoNoConfigCommandHandler implements CommandHandler<DeleteAutoNoConfigCommand, HttpResult> {

  private final AutoNoConfigManager configManager;
  private final AutoNoDefineManager defineManager;

  public DeleteAutoNoConfigCommandHandler(AutoNoConfigManager configManager,
                                          AutoNoDefineManager defineManager) {
    this.configManager = configManager;
    this.defineManager = defineManager;
  }

  @Transactional
  public HttpResult execute(DeleteAutoNoConfigCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdPayload payload = Optional.ofNullable(command.getPayload()).orElse(IdPayload.create());
    String configId = Optional.ofNullable(command.getConfigId()).orElse(payload.getId());

    List<AutoNoConfigEntity> entities = configManager.selectList(w -> {
      if (StringUtils.hasText(configId)) {
        w.eq(AutoNoConfigEntity::getConfigId, configId);
      } else if (CollectionUtils.isNotEmpty(payload.getIds())) {
        w.in(AutoNoConfigEntity::getConfigId, payload.getIds());
      }
    });

    if (CollectionUtils.isNotEmpty(entities)) {
      for (AutoNoConfigEntity entity : entities) {
        if (loginUser.hasRootOrAdmin()) {
          if (UaspConstants.GLOBAL.equalsIgnoreCase(entity.getTenantId())) {
            defineManager.deleteById(entity.getDefineId());
          } else {
            configManager.deleteById(entity.getConfigId());
          }
        } else if (Objects.equals(loginUser.getTenantId(), entity.getTenantId())) {
          configManager.deleteById(entity.getConfigId());
        }
      }
    }

    return HttpResult.ok();
  }

}
