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
