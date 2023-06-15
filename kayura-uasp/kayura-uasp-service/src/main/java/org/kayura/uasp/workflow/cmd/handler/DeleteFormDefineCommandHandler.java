/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
 -----------------------------------------------------------------------------*/
package org.kayura.uasp.workflow.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.workflow.cmd.DeleteFormDefineCommand;
import org.kayura.uasp.workflow.manage.FormDefineManager;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DeleteFormDefineCommandHandler implements CommandHandler<DeleteFormDefineCommand, HttpResult> {

  private final FormDefineManager defineManager;

  public DeleteFormDefineCommandHandler(FormDefineManager defineManager) {
    this.defineManager = defineManager;
  }

  @Transactional
  public HttpResult execute(DeleteFormDefineCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdPayload payload = Optional.ofNullable(command.getPayload()).orElse(IdPayload.create());
    String formId = Optional.ofNullable(command.getFormId()).orElse(payload.getId());

    if (CollectionUtils.isNotEmpty(payload.getIds())) {
      defineManager.deleteByIds(payload.getIds());
    } else if (StringUtils.hasText(formId)) {
      defineManager.deleteById(formId);
    }

    return HttpResult.ok();
  }
}
