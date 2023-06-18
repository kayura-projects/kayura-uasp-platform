/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
