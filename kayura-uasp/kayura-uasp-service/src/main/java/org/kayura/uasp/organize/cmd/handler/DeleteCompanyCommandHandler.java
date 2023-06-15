package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.organize.cmd.DeleteCompanyCommand;
import org.kayura.uasp.organize.manage.CompanyManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DeleteCompanyCommandHandler implements CommandHandler<DeleteCompanyCommand, HttpResult> {

  private final CompanyManager companyManager;

  public DeleteCompanyCommandHandler(CompanyManager companyManager) {
    this.companyManager = companyManager;
  }

  @Transactional
  public HttpResult execute(DeleteCompanyCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdPayload payload = Optional.ofNullable(command.getPayload())
      .orElse(IdPayload.create().setId(command.getCompanyId()));

    companyManager.deleteForPayload(payload);

    return HttpResult.ok();
  }
}
