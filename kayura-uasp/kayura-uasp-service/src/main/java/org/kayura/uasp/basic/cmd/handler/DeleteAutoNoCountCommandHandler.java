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
