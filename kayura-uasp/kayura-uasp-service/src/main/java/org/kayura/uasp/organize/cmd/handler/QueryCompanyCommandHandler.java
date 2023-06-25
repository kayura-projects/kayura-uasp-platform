/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
