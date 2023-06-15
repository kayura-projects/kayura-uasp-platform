package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.organize.cmd.DeleteCompanyApplicCommand;
import org.kayura.uasp.organize.manage.CompanyApplicManager;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DeleteCompanyApplicCommandHandler implements CommandHandler<DeleteCompanyApplicCommand, HttpResult> {

  private final CompanyApplicManager companyApplicManager;

  public DeleteCompanyApplicCommandHandler(CompanyApplicManager companyApplicManager) {
    this.companyApplicManager = companyApplicManager;
  }

  @Transactional
  public HttpResult execute(DeleteCompanyApplicCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdPayload payload = Optional.ofNullable(command.getPayload()).orElse(IdPayload.create());

    if (StringUtils.hasText(payload.getId())) {
      companyApplicManager.deleteById(payload.getId());
    } else if (CollectionUtils.isNotEmpty(payload.getIds())) {
      companyApplicManager.deleteByIds(payload.getIds());
    }

    return HttpResult.ok();
  }
}
