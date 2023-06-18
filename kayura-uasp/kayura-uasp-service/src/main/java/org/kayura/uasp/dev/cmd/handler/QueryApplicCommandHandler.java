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

package org.kayura.uasp.dev.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.mybatis.manager.Chain.LambdaQueryChainWrapper;
import org.kayura.type.HttpResult;
import org.kayura.type.OrderByClause;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.applic.ApplicQuery;
import org.kayura.uasp.applic.ApplicVo;
import org.kayura.uasp.dev.cmd.QueryApplicCommand;
import org.kayura.uasp.dev.entity.ApplicEntity;
import org.kayura.uasp.dev.manage.ApplicManager;
import org.kayura.uasp.organize.entity.CompanyApplicEntity;
import org.kayura.uasp.organize.manage.CompanyApplicManager;
import org.kayura.uasp.utils.UaspConstants;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class QueryApplicCommandHandler implements CommandHandler<QueryApplicCommand, HttpResult> {

  private final ModelMapper modelMapper;
  private final ApplicManager applicManager;
  private final CompanyApplicManager companyApplicManager;

  public QueryApplicCommandHandler(ModelMapper modelMapper,
                                   ApplicManager applicManager,
                                   CompanyApplicManager companyApplicManager) {
    this.modelMapper = modelMapper;
    this.applicManager = applicManager;
    this.companyApplicManager = companyApplicManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryApplicCommand command) {

    String companyId = command.getCompanyId();
    PageClause pageClause = command.getPageClause();

    List<String> appIds;
    if (StringUtils.hasText(companyId)) {
      appIds = companyApplicManager.selectList(w -> {
        w.select(CompanyApplicEntity::getAppId);
        w.eq(CompanyApplicEntity::getCompanyId, companyId);
      }).stream().map(CompanyApplicEntity::getAppId).toList();
      if (appIds.isEmpty()) {
        return HttpResult.okBody(new ArrayList<>());
      }
    } else {
      appIds = null;
    }

    if (pageClause != null) {
      PageList<ApplicVo> pageList = applicManager.selectPage(w -> {
        this.addonsWhere(command, appIds, w);
      }, pageClause).streamMap(m -> modelMapper.map(m, ApplicVo.class));
      return HttpResult.okBody(pageList);
    } else {
      OrderByClause orderByClause = command.getOrderByClause();
      List<ApplicVo> list = applicManager.selectList(w -> {
        this.addonsWhere(command, appIds, w);
        w.orderByOf(orderByClause);
      }).stream().map(m -> modelMapper.map(m, ApplicVo.class)).toList();
      return HttpResult.okBody(list);
    }
  }

  protected void addonsWhere(QueryApplicCommand command, List<String> appIds, LambdaQueryChainWrapper<ApplicEntity> w) {

    Boolean notUasp = command.getNotUasp();
    ApplicQuery query = command.getQuery();

    if (Boolean.TRUE.equals(notUasp)) {
      w.notEq(ApplicEntity::getAppId, UaspConstants.UASP_APP_ID);
    }

    if (CollectionUtils.isNotEmpty(appIds)) {
      w.in(ApplicEntity::getAppId, appIds);
    }

    w.of(query);
  }
}
