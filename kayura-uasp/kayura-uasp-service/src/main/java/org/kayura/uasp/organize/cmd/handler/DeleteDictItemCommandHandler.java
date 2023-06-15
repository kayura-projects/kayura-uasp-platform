package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.basic.manage.DictItemManager;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.organize.cmd.DeleteDictItemCommand;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DeleteDictItemCommandHandler implements CommandHandler<DeleteDictItemCommand, HttpResult> {

  private final DictItemManager itemManager;

  public DeleteDictItemCommandHandler(DictItemManager itemManager) {
    this.itemManager = itemManager;
  }

  @Transactional
  public HttpResult execute(DeleteDictItemCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdPayload payload = Optional.ofNullable(command.getPayload()).orElse(IdPayload.create());
    String defineId = Optional.ofNullable(command.getItemId()).orElse(payload.getId());

    if (StringUtils.hasText(defineId)) {
      itemManager.deleteById(defineId);
    } else if (CollectionUtils.isNotEmpty(payload.getIds())) {
      itemManager.deleteByIds(payload.getIds());
    }

    return HttpResult.ok();
  }

}
