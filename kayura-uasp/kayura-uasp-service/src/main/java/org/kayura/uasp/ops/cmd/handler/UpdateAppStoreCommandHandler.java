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
