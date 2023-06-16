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
import org.kayura.uasp.basic.cmd.DeleteAutoNoCountCommand;
import org.kayura.uasp.basic.manage.AutoNoCountManager;
import org.kayura.uasp.common.IdPayload;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DeleteAutoNoCountCommandHandler implements CommandHandler<DeleteAutoNoCountCommand, HttpResult> {

  private final AutoNoCountManager countManager;

  public DeleteAutoNoCountCommandHandler(AutoNoCountManager countManager) {
    this.countManager = countManager;
  }

  @Transactional
  public HttpResult execute(DeleteAutoNoCountCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdPayload payload = Optional.ofNullable(command.getPayload()).orElse(IdPayload.create());
    String countId = Optional.ofNullable(command.getCountId()).orElse(payload.getId());

    if (StringUtils.hasText(countId)) {
      countManager.deleteById(countId);
    } else if (CollectionUtils.isNotEmpty(payload.getIds())) {
      countManager.deleteByIds(payload.getIds());
    }

    return HttpResult.ok();
  }

}
