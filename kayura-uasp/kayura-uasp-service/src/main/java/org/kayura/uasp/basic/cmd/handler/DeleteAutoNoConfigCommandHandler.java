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
