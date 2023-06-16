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
