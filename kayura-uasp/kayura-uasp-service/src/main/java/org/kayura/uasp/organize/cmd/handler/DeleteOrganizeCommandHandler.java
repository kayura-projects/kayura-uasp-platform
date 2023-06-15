package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.Result;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.organize.OrganizeTypes;
import org.kayura.uasp.organize.cmd.DeleteOrganizeCommand;
import org.kayura.uasp.organize.manage.CompanyManager;
import org.kayura.uasp.organize.manage.DepartManager;
import org.kayura.uasp.organize.manage.PositionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DeleteOrganizeCommandHandler implements CommandHandler<DeleteOrganizeCommand, HttpResult> {

  private final CompanyManager companyManager;
  private final DepartManager departManager;
  private final PositionManager positionManager;

  public DeleteOrganizeCommandHandler(CompanyManager companyManager,
                                      DepartManager departManager,
                                      PositionManager positionManager) {
    this.companyManager = companyManager;
    this.departManager = departManager;
    this.positionManager = positionManager;
  }

  @Transactional
  public HttpResult execute(DeleteOrganizeCommand command) {

    LoginUser loginUser = command.getLoginUser();
    OrganizeTypes type = command.getType();
    IdPayload payload = Optional.ofNullable(command.getPayload())
      .orElse(IdPayload.create().setId(command.getOrgId()));

    Result result;
    if (OrganizeTypes.Company.equals(type)) {
      result = companyManager.deleteForPayload(payload);
    } else if (OrganizeTypes.Depart.equals(type)) {
      result = departManager.deleteForPayload(payload);
    } else {
      result = positionManager.deleteForPayload(payload);
    }
    return HttpResult.of(result);
  }


}
