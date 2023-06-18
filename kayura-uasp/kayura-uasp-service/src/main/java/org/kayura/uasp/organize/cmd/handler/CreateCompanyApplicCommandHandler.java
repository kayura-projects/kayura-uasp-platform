/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
