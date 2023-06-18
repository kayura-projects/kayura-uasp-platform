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
