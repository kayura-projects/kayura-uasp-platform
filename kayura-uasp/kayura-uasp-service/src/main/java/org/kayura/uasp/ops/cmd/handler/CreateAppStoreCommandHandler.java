package org.kayura.uasp.ops.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.applibrary.AppStorePayload;
import org.kayura.uasp.ops.cmd.CreateAppStoreCommand;
import org.kayura.uasp.ops.entity.AppStoreEntity;
import org.kayura.uasp.ops.manage.AppStoreManager;
import org.kayura.utils.Assert;
import org.kayura.utils.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateAppStoreCommandHandler implements CommandHandler<CreateAppStoreCommand, HttpResult> {

  private final AppStoreManager appStoreManager;

  public CreateAppStoreCommandHandler(AppStoreManager appStoreManager) {
    this.appStoreManager = appStoreManager;
  }

  @Transactional
  public HttpResult execute(CreateAppStoreCommand command) {

    LoginUser loginUser = command.getLoginUser();
    AppStorePayload payload = command.getPayload();

    AppStoreEntity store = appStoreManager.selectOne(w -> {
      w.eq(AppStoreEntity::getAppId, payload.getAppId());
      w.orderByDesc(AppStoreEntity::getVersion);
    });

    if (store != null) {
      Assert.isTrue(Boolean.TRUE.equals(store.getReleased()), "最近一个版本尚未发布。");
    }

    AppStoreEntity entity = AppStoreEntity.create()
      .setReleaseId(appStoreManager.nextId())
      .setAppId(payload.getAppId())
      .setForced(Boolean.TRUE.equals(payload.getForced()))
      .setMode(payload.getMode())
      .setTitle(payload.getTitle())
      .setDescription(payload.getDescription())
      .setResourceId(payload.getResourceId())
      .setVersion(store != null ? store.getVersion() + 1 : 1)
      .setReleased(Boolean.TRUE.equals(payload.getReleased()))
      .setCreatorId(loginUser.getUserId())
      .setCreateTime(DateUtils.now());
    appStoreManager.insertOne(entity);

    return HttpResult.ok();
  }

}
