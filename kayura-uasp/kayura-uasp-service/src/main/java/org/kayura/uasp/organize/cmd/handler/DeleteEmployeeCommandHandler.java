package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.organize.cmd.DeleteEmployeeCommand;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DeleteEmployeeCommandHandler implements CommandHandler<DeleteEmployeeCommand, HttpResult> {

  private final UserManager userManager;

  public DeleteEmployeeCommandHandler(UserManager userManager) {
    this.userManager = userManager;
  }

  @Transactional
  public HttpResult execute(DeleteEmployeeCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdPayload payload = Optional.ofNullable(command.getPayload()).orElse(IdPayload.create());
    String id = Optional.ofNullable(command.getEmployeeId()).orElse(payload.getId());

    if (StringUtils.hasText(id)) {
      userManager.deleteById(id);
    } else if (CollectionUtils.isNotEmpty(payload.getIds())) {
      userManager.deleteByIds(payload.getIds());
    }

    return HttpResult.ok();
  }
}
