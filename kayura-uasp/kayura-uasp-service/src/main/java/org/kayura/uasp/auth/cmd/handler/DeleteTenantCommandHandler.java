package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.auth.cmd.DeleteTenantCommand;
import org.kayura.uasp.organize.entity.CompanyEntity;
import org.kayura.uasp.organize.manage.CompanyManager;
import org.kayura.uasp.common.IdPayload;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DeleteTenantCommandHandler implements CommandHandler<DeleteTenantCommand, HttpResult> {

  private final CompanyManager companyManager;

  public DeleteTenantCommandHandler(CompanyManager companyManager) {
    this.companyManager = companyManager;
  }

  @Transactional
  public HttpResult execute(DeleteTenantCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdPayload payload = command.getPayload();

    if (CollectionUtils.isEmpty(payload.getIds()) && StringUtils.isBlank(payload.getId())) {
      return HttpResult.error("必需指定一个删除条件。");
    }

    List<String> deleteIds = companyManager.selectList(w -> {
      w.select(CompanyEntity::getCompanyId);
      if (CollectionUtils.isNotEmpty(payload.getIds())) {
        w.in(CompanyEntity::getCompanyId, payload.getIds());
      } else {
        w.eq(CompanyEntity::getCompanyId, payload.getId());
      }
      if (StringUtils.hasText(loginUser.getTenantId())) {
        w.eq(CompanyEntity::getTenantId, loginUser.getTenantId());
      }
    }).stream().map(CompanyEntity::getCompanyId).toList();

    // delete
    if (!deleteIds.isEmpty()) {
      companyManager.deleteByIds(deleteIds);
    }

    return HttpResult.ok();
  }

}
