/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

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
