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
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.auth.cmd.QueryTenantCommand;
import org.kayura.uasp.auth.manage.TenantManager;
import org.kayura.uasp.tenant.TenantQuery;
import org.kayura.uasp.tenant.TenantVo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class QueryTenantCommandHandler implements CommandHandler<QueryTenantCommand, HttpResult> {

  private final TenantManager tenantManager;
  private final ModelMapper modelMapper;

  public QueryTenantCommandHandler(TenantManager tenantManager,
                                   ModelMapper modelMapper) {
    this.tenantManager = tenantManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryTenantCommand command) {

    TenantQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    PageList<TenantVo> pageList = tenantManager.selectPage(w -> {
      w.of(query);
    }, pageClause).streamMap(m -> modelMapper.map(m, TenantVo.class));
    return HttpResult.okBody(pageList);
  }

}
