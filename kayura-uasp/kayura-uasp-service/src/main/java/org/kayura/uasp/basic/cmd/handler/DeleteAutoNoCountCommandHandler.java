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
