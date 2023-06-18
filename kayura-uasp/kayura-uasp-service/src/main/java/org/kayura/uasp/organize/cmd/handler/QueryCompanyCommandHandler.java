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
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.company.CompanyQuery;
import org.kayura.uasp.company.CompanyVo;
import org.kayura.uasp.organize.cmd.QueryCompanyCommand;
import org.kayura.uasp.organize.manage.CompanyManager;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class QueryCompanyCommandHandler implements CommandHandler<QueryCompanyCommand, HttpResult> {

  private final CompanyManager companyManager;
  private final ModelMapper modelMapper;

  public QueryCompanyCommandHandler(CompanyManager companyManager,
                                    ModelMapper modelMapper) {
    this.companyManager = companyManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryCompanyCommand command) {

    LoginUser loginUser = command.getLoginUser();
    CompanyQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    if (StringUtils.hasText(loginUser.getTenantId())) {
      query.setTenantId(loginUser.getTenantId());
    }

    PageList<CompanyVo> pageList = companyManager.selectPage(w -> {
      w.of(query);
    }, pageClause).streamMap(m -> modelMapper.map(m, CompanyVo.class));
    return HttpResult.okBody(pageList);
  }

}
