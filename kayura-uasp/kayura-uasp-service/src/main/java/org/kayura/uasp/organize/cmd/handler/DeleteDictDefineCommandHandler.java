package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.UserTypes;
import org.kayura.uasp.basic.manage.DictDefineManager;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.organize.cmd.DeleteDictDefineCommand;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DeleteDictDefineCommandHandler implements CommandHandler<DeleteDictDefineCommand, HttpResult> {

  private final DictDefineManager defineManager;

  public DeleteDictDefineCommandHandler(DictDefineManager defineManager) {
    this.defineManager = defineManager;
  }

  @Transactional
  public HttpResult execute(DeleteDictDefineCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdPayload payload = Optional.ofNullable(command.getPayload()).orElse(IdPayload.create());
    String defineId = Optional.ofNullable(command.getDefineId()).orElse(payload.getId());

    if (!UserTypes.ROOT.equals(loginUser.getUserType())) {
      return HttpResult.error("只能ROOT账号可以调用。");
    }

    if (StringUtils.hasText(defineId)) {
      defineManager.deleteById(defineId);
    } else if (CollectionUtils.isNotEmpty(payload.getIds())) {
      defineManager.deleteByIds(payload.getIds());
    }

    return HttpResult.ok();
  }

}
