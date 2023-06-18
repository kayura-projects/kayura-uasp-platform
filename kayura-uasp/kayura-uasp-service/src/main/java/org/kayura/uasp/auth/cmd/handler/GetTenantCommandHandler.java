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
