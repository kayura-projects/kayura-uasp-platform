package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.type.HttpResult;
import org.kayura.uasp.auth.cmd.GetTenantCommand;
import org.kayura.uasp.organize.entity.CompanyEntity;
import org.kayura.uasp.auth.entity.TenantEntity;
import org.kayura.uasp.organize.manage.CompanyManager;
import org.kayura.uasp.auth.manage.TenantManager;
import org.kayura.uasp.company.CompanyVo;
import org.kayura.uasp.tenant.TenantVo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GetTenantCommandHandler implements CommandHandler<GetTenantCommand, HttpResult> {

  private final TenantManager tenantManager;
  private final ModelMapper modelMapper;
  private final CompanyManager companyManager;

  public GetTenantCommandHandler(TenantManager tenantManager,
                                 ModelMapper modelMapper,
                                 CompanyManager companyManager) {
    this.tenantManager = tenantManager;
    this.modelMapper = modelMapper;
    this.companyManager = companyManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(GetTenantCommand command) {

    String tenantId = command.getTenantId();

    TenantEntity entity = tenantManager.selectById(tenantId);
    TenantVo model = modelMapper.map(entity, TenantVo.class);
    if (model != null) {
      CompanyEntity company = companyManager.selectById(tenantId);
      model.setCompany(modelMapper.map(company, CompanyVo.class));
    }

    return HttpResult.okBody(model);
  }

}
