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
import org.kayura.type.HttpResult;
import org.kayura.type.OrderByClause;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.organize.cmd.QueryCompanyApplicCommand;
import org.kayura.uasp.organize.entity.CompanyApplicEntity;
import org.kayura.uasp.organize.manage.CompanyApplicManager;
import org.kayura.uasp.company.CompanyApplicQuery;
import org.kayura.uasp.company.CompanyApplicVo;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class QueryCompanyApplicCommandHandler implements CommandHandler<QueryCompanyApplicCommand, HttpResult> {

  private final CompanyApplicManager companyApplicManager;
  private final ModelMapper modelMapper;

  public QueryCompanyApplicCommandHandler(CompanyApplicManager companyApplicManager,
                                          ModelMapper modelMapper) {
    this.companyApplicManager = companyApplicManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryCompanyApplicCommand command) {

    String applicId = command.getAppId();
    String companyId = command.getCompanyId();
    CompanyApplicQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    if (pageClause != null) {

      PageList<CompanyApplicVo> pageList = companyApplicManager.selectPage(w -> {
        if (StringUtils.hasText(companyId)) {
          w.eq(CompanyApplicEntity::getCompanyId, companyId);
        }
        if (StringUtils.hasText(applicId)) {
          w.eq(CompanyApplicEntity::getAppId, applicId);
        }
        w.of(query);
      }, pageClause).streamMap(m -> modelMapper.map(m, CompanyApplicVo.class));
      return HttpResult.okBody(pageList);

    } else {

      OrderByClause orderByClause = command.getOrderByClause();
      List<CompanyApplicVo> list = companyApplicManager.selectList(w -> {
        if (StringUtils.hasText(companyId)) {
          w.eq(CompanyApplicEntity::getCompanyId, command);
        }
        if (StringUtils.hasText(applicId)) {
          w.eq(CompanyApplicEntity::getAppId, applicId);
        }
        w.of(query);
        w.orderByOf(orderByClause);
      }).stream().map(m -> modelMapper.map(m, CompanyApplicVo.class)).toList();
      return HttpResult.okBody(list);

    }
  }

}
