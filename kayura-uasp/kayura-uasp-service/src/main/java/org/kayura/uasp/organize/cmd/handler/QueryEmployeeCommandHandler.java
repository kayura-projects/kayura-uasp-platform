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
import org.kayura.uasp.organize.EmployeeQuery;
import org.kayura.uasp.organize.EmployeeVo;
import org.kayura.uasp.organize.OrganizeTypes;
import org.kayura.uasp.organize.cmd.QueryEmployeeCommand;
import org.kayura.uasp.organize.entity.DepartEntity;
import org.kayura.uasp.organize.entity.EmployeeEntity;
import org.kayura.uasp.organize.entity.IdentityEntity;
import org.kayura.uasp.organize.manage.DepartManager;
import org.kayura.uasp.organize.manage.EmployeeManager;
import org.kayura.uasp.organize.manage.IdentityManager;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class QueryEmployeeCommandHandler implements CommandHandler<QueryEmployeeCommand, HttpResult> {

  private final IdentityManager identityManager;
  private final DepartManager departManager;
  private final EmployeeManager employeeManager;
  private final ModelMapper modelMapper;

  public QueryEmployeeCommandHandler(IdentityManager identityManager,
                                     DepartManager departManager,
                                     EmployeeManager employeeManager,
                                     ModelMapper modelMapper) {
    this.identityManager = identityManager;
    this.departManager = departManager;
    this.employeeManager = employeeManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryEmployeeCommand command) {

    LoginUser loginUser = command.getLoginUser();
    EmployeeQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    Set<String> employeeIds = null;
    Set<String> allDepartIds = null;

    if (OrganizeTypes.Position.equals(query.getOrgType())) {
      employeeIds = identityManager.selectList(w -> {
        w.distinct();
        w.select(IdentityEntity::getEmployeeId);
        w.eq(IdentityEntity::getPositionId, query.getOrgId());
      }).stream().map(IdentityEntity::getEmployeeId).collect(Collectors.toSet());
      if (CollectionUtils.isEmpty(employeeIds)) {
        return HttpResult.okBody(PageList.buildEmpty());
      }
    } else if (OrganizeTypes.Depart.equals(query.getOrgType())) {
      allDepartIds = new HashSet<>(Collections.singleton(query.getOrgId()));
      this.seekAllSubDepartIds(allDepartIds, allDepartIds);
    }

    final Set<String> finalEmployeeIds = employeeIds;
    final Set<String> finalAllDepartIds = allDepartIds;
    PageList<EmployeeVo> pageList = employeeManager.selectPage(w -> {
      w.select(EmployeeEntity::getEmployeeId);
      w.select(EmployeeEntity::getCompanyId);
      w.select(EmployeeEntity::getJobNo);
      w.select(EmployeeEntity::getUserName);
      w.select(EmployeeEntity::getRealName);
      w.select(EmployeeEntity::getMobile);
      w.select(EmployeeEntity::getDepartId);
      w.select(EmployeeEntity::getDepartName);
      w.select(EmployeeEntity::getStatus);
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
    }, pageClause).streamMap(m -> modelMapper.map(m, EmployeeVo.class));
    return HttpResult.okBody(pageList);
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
