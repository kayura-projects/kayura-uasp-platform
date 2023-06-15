package org.kayura.uasp.ops.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.ops.cmd.DeleteAppStoreCommand;
import org.kayura.uasp.ops.manage.AppStoreManager;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DeleteAppStoreCommandHandler implements CommandHandler<DeleteAppStoreCommand, HttpResult> {

  private final AppStoreManager appStoreManager;

  public DeleteAppStoreCommandHandler(AppStoreManager appStoreManager) {
    this.appStoreManager = appStoreManager;
  }

  @Transactional
  public HttpResult execute(DeleteAppStoreCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdPayload payload = Optional.ofNullable(command.getPayload()).orElse(IdPayload.create());
    String releaseId = Optional.ofNullable(command.getReleaseId()).orElse(payload.getId());

    if (StringUtils.hasText(releaseId)) {
      appStoreManager.deleteById(releaseId);
    } else if (CollectionUtils.isNotEmpty(payload.getIds())) {
      appStoreManager.deleteByIds(payload.getIds());
    }

    return HttpResult.ok();
  }

}
