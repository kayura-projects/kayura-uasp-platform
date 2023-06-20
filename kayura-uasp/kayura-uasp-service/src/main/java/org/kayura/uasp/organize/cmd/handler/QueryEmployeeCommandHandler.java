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
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.applic.ApplicVo;
import org.kayura.uasp.dev.entity.ApplicEntity;
import org.kayura.uasp.dev.manage.ApplicManager;
import org.kayura.uasp.organize.EmployeeQuery;
import org.kayura.uasp.organize.EmployeeVo;
import org.kayura.uasp.organize.OrganizeTypes;
import org.kayura.uasp.organize.cmd.QueryEmployeeCommand;
import org.kayura.uasp.organize.entity.CompanyApplicEntity;
import org.kayura.uasp.organize.entity.DepartEntity;
import org.kayura.uasp.organize.entity.EmployeeEntity;
import org.kayura.uasp.organize.entity.IdentityEntity;
import org.kayura.uasp.organize.manage.CompanyApplicManager;
import org.kayura.uasp.organize.manage.DepartManager;
import org.kayura.uasp.organize.manage.EmployeeManager;
import org.kayura.uasp.organize.manage.IdentityManager;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class QueryEmployeeCommandHandler implements CommandHandler<QueryEmployeeCommand, HttpResult> {

  private final IdentityManager identityManager;
  private final DepartManager departManager;
  private final EmployeeManager employeeManager;
  private final ModelMapper modelMapper;
  private final ApplicManager applicManager;
  private final CompanyApplicManager companyApplicManager;

  public QueryEmployeeCommandHandler(IdentityManager identityManager,
                                     DepartManager departManager,
                                     EmployeeManager employeeManager,
                                     ModelMapper modelMapper,
                                     ApplicManager applicManager,
                                     CompanyApplicManager companyApplicManager) {
    this.identityManager = identityManager;
    this.departManager = departManager;
    this.employeeManager = employeeManager;
    this.modelMapper = modelMapper;
    this.applicManager = applicManager;
    this.companyApplicManager = companyApplicManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryEmployeeCommand command) {

    // load employees
    PageList<EmployeeVo> pageList = this.loadEmployeePages(command);

    // load applic and tenant
    this.loadEmployeeForApplics(command, pageList.getRows());

    // result
    return HttpResult.okBody(pageList);
  }

  private PageList<EmployeeVo> loadEmployeePages(QueryEmployeeCommand command) {

    LoginUser loginUser = command.getLoginUser();
    EmployeeQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    Set<String> limitEmployeeIds = null;
    Set<String> limitDepartIds = null;

    if (OrganizeTypes.Position.equals(query.getOrgType())) {
      limitEmployeeIds = identityManager.selectList(w -> {
        w.distinct();
        w.select(IdentityEntity::getEmployeeId);
        w.eq(IdentityEntity::getPositionId, query.getOrgId());
      }).stream().map(IdentityEntity::getEmployeeId).collect(Collectors.toSet());
      if (CollectionUtils.isEmpty(limitEmployeeIds)) {
        return PageList.buildEmpty();
      }
    } else if (OrganizeTypes.Depart.equals(query.getOrgType())) {
      limitDepartIds = new HashSet<>(Collections.singleton(query.getOrgId()));
      this.seekAllSubDepartIds(limitDepartIds, limitDepartIds);
    }

    final Set<String> finalEmployeeIds = limitEmployeeIds;
    final Set<String> finalAllDepartIds = limitDepartIds;

    Function<EmployeeEntity, EmployeeVo> employeeConverter =
      Optional.ofNullable(command.getEmployeeConverter()).orElse(m -> modelMapper.map(m, EmployeeVo.class));

    PageList<EmployeeVo> pageList = employeeManager.selectPage(w -> {
      w.of(query);
      if (StringUtils.hasText(loginUser.getTenantId())) {
        w.eq(EmployeeEntity::getTenantId, loginUser.getTenantId());
      }
      if (OrganizeTypes.Company.equals(query.getOrgType())) {
        w.eq(EmployeeEntity::getCompanyId, query.getOrgId());
      }
      if (OrganizeTypes.Depart.equals(query.getOrgType())) {
        w.in(EmployeeEntity::getDepartId, finalAllDepartIds);
      }
      if (OrganizeTypes.Position.equals(query.getOrgType())) {
        w.in(EmployeeEntity::getEmployeeId, finalEmployeeIds);
      }
    }, pageClause).streamMap(employeeConverter);

    return pageList;
  }

  private void loadEmployeeForApplics(QueryEmployeeCommand command, List<EmployeeVo> rows) {

    boolean includeApplic = command.isIncludeApplic();
    Function<ApplicEntity, ApplicVo> applicConverter =
      Optional.ofNullable(command.getApplicConverter()).orElse(m -> modelMapper.map(m, ApplicVo.class));

    if (includeApplic && !rows.isEmpty()) {
      List<String> companyIds = rows.stream()
        .map(EmployeeVo::getCompanyId)
        .filter(StringUtils::isNotBlank).toList();
      if (!companyIds.isEmpty()) {
        List<CompanyApplicEntity> list = companyApplicManager.selectList(w -> {
          w.in(CompanyApplicEntity::getCompanyId, companyIds);
        });
        Set<String> allAppIds = list.stream().map(CompanyApplicEntity::getAppId).collect(Collectors.toSet());
        List<ApplicEntity> applicEntities = applicManager.selectList(w -> w.in(ApplicEntity::getAppId, allAppIds));
        for (EmployeeVo emp : rows) {
          List<String> limitAppIds = list.stream()
            .filter(x -> Objects.equals(x.getCompanyId(), emp.getCompanyId()))
            .map(CompanyApplicEntity::getAppId).toList();
          List<ApplicVo> applics = applicEntities.stream().filter(x -> limitAppIds.contains(x.getAppId()))
            .map(applicConverter).toList();
          emp.setApplics(applics);
        }
      }
    }
  }

  protected void seekAllSubDepartIds(Set<String> allDepartIds, Set<String> ids) {

    Set<String> collect = departManager.selectList(w -> {
        w.select(DepartEntity::getDepartId);
        w.in(DepartEntity::getParentId, ids);
      }).stream().map(DepartEntity::getDepartId)
      .collect(Collectors.toSet());
    if (!collect.isEmpty()) {
      allDepartIds.addAll(collect);
      this.seekAllSubDepartIds(allDepartIds, collect);
    }
  }
}
