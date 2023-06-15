package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.organize.cmd.UpdateCompanyApplicCommand;
import org.kayura.uasp.organize.entity.CompanyApplicEntity;
import org.kayura.uasp.organize.manage.CompanyApplicManager;
import org.kayura.uasp.company.CompanyApplicPayload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateCompanyApplicCommandHandler implements CommandHandler<UpdateCompanyApplicCommand, HttpResult> {

  private final CompanyApplicManager companyApplicManager;

  public UpdateCompanyApplicCommandHandler(CompanyApplicManager companyApplicManager) {
    this.companyApplicManager = companyApplicManager;
  }

  @Transactional
  public HttpResult execute(UpdateCompanyApplicCommand command) {

    LoginUser loginUser = command.getLoginUser();
    CompanyApplicPayload payload = command.getPayload();

    CompanyApplicEntity entity = companyApplicManager.selectById(payload.getId());
    if (entity != null) {
      entity.setExpireTime(payload.getExpireTime());
      entity.setStatus(payload.getStatus());
      companyApplicManager.updateById(payload.getId(), entity);
    }

    return HttpResult.ok();
  }

}
