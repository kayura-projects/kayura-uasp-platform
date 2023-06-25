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
