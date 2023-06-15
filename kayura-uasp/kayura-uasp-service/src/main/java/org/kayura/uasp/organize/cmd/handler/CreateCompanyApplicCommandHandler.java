package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.UsableStatus;
import org.kayura.uasp.organize.cmd.CreateCompanyApplicCommand;
import org.kayura.uasp.organize.entity.CompanyApplicEntity;
import org.kayura.uasp.organize.manage.CompanyApplicManager;
import org.kayura.uasp.company.CompanyApplicPayload;
import org.kayura.utils.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class CreateCompanyApplicCommandHandler implements CommandHandler<CreateCompanyApplicCommand, HttpResult> {

  private final CompanyApplicManager companyApplicManager;

  public CreateCompanyApplicCommandHandler(CompanyApplicManager companyApplicManager) {
    this.companyApplicManager = companyApplicManager;
  }

  @Transactional
  public HttpResult execute(CreateCompanyApplicCommand command) {

    LoginUser loginUser = command.getLoginUser();
    CompanyApplicPayload payload = command.getPayload();
    List<CompanyApplicPayload> payloads = Optional.ofNullable(command.getPayloads())
      .orElse(payload != null ? Collections.singletonList(payload) : new ArrayList<>());

    for (CompanyApplicPayload row : payloads) {
      if (companyApplicManager.selectCount(w -> {
        w.eq(CompanyApplicEntity::getCompanyId, row.getCompanyId());
        w.eq(CompanyApplicEntity::getAppId, row.getAppId());
      }) == 0) {
        CompanyApplicEntity entity = CompanyApplicEntity.create();
        entity.setId(companyApplicManager.nextId());
        entity.setCompanyId(row.getCompanyId());
        entity.setAppId(row.getAppId());
        entity.setCreateTime(DateUtils.now());
        entity.setCreatorId(loginUser.getUserId());
        entity.setExpireTime(row.getExpireTime());
        entity.setStatus(Optional.ofNullable(row.getStatus()).orElse(UsableStatus.Valid));
        companyApplicManager.insertOne(entity);
      }
    }

    return HttpResult.ok();
  }

}
