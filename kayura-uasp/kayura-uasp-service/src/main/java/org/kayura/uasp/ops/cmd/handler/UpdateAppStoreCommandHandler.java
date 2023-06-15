package org.kayura.uasp.ops.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.applibrary.AppStorePayload;
import org.kayura.uasp.ops.cmd.UpdateAppStoreCommand;
import org.kayura.uasp.ops.entity.AppStoreEntity;
import org.kayura.uasp.ops.manage.AppStoreManager;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class UpdateAppStoreCommandHandler implements CommandHandler<UpdateAppStoreCommand, HttpResult> {

  private final AppStoreManager appStoreManager;

  public UpdateAppStoreCommandHandler(AppStoreManager appStoreManager) {
    this.appStoreManager = appStoreManager;
  }

  @Transactional
  public HttpResult execute(UpdateAppStoreCommand command) {

    LoginUser loginUser = command.getLoginUser();
    AppStorePayload payload = command.getPayload();
    String releaseId = Optional.ofNullable(command.getReleaseId()).orElse(payload.getReleaseId());

    if (Boolean.TRUE.equals(payload.getReleased()) && StringUtils.isBlank(payload.getResourceId())) {
      return HttpResult.error("发布应用时必需提供下载的文件。");
    }

    AppStoreEntity entity = appStoreManager.selectById(releaseId);
    if (entity == null) {
      return HttpResult.error("更新的应用不存在。");
    }

    entity.setForced(payload.getForced());
    entity.setMode(payload.getMode());
    entity.setTitle(payload.getTitle());
    entity.setDescription(payload.getDescription());
    entity.setResourceId(payload.getResourceId());
    entity.setReleased(payload.getReleased());
    if (Boolean.TRUE.equals(payload.getReleased())) {
      entity.setReleaseTime(LocalDateTime.now());
    }
    entity.setUpdaterId(loginUser.getUserId());
    entity.setUpdateTime(LocalDateTime.now());
    appStoreManager.updateById(releaseId, entity);

    return HttpResult.ok();
  }

}
