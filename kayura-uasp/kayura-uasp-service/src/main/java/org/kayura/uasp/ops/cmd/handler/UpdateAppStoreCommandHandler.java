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
