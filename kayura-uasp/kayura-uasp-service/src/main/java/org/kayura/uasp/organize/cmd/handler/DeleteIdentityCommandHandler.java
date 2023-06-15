package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.organize.cmd.DeleteIdentityCommand;
import org.kayura.uasp.organize.manage.IdentityManager;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
public class DeleteIdentityCommandHandler implements CommandHandler<DeleteIdentityCommand, HttpResult> {

  private final IdentityManager identityManager;

  public DeleteIdentityCommandHandler(IdentityManager identityManager) {
    this.identityManager = identityManager;
  }

  @Transactional
  public HttpResult execute(DeleteIdentityCommand command) {

    LoginUser loginUser = command.getLoginUser();
    String identityId = command.getIdentityId();
    Set<String> identityIds = command.getIdentityIds();

    if (StringUtils.hasText(identityId)) {
      identityManager.deleteById(identityId);
    } else if (CollectionUtils.isNotEmpty(identityIds)) {
      identityManager.deleteByIds(identityIds);
    }

    return HttpResult.ok();
  }
}
