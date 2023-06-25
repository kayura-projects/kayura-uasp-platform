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

package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.mybatis.manager.Chain.LambdaQueryChainWrapper;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.OrderByClause;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.auth.cmd.QueryRoleCommand;
import org.kayura.uasp.auth.entity.RoleEntity;
import org.kayura.uasp.auth.manage.RoleManager;
import org.kayura.uasp.role.RoleQuery;
import org.kayura.uasp.role.RoleVo;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class QueryRoleCommandHandler implements CommandHandler<QueryRoleCommand, HttpResult> {

  private final RoleManager roleManager;
  private final ModelMapper modelMapper;

  public QueryRoleCommandHandler(RoleManager roleManager,
                                 ModelMapper modelMapper) {
    this.roleManager = roleManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryRoleCommand command) {

    LoginUser loginUser = command.getLoginUser();
    RoleQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    if (StringUtils.hasText(loginUser.getTenantId())) {
      query.setTenantId(loginUser.getTenantId());
      query.setAppId(loginUser.getAppId());
    }

    if (pageClause != null) {

      PageList<RoleVo> pageList = roleManager.selectPage(w -> {
        this.addonsWhere(command, w);
        w.of(query);
      }, pageClause).streamMap(m -> modelMapper.map(m, RoleVo.class));
      return HttpResult.okBody(pageList);

    } else {

      OrderByClause orderByClause = command.getOrderByClause();
      List<RoleVo> pageList = roleManager.selectList(w -> {
          this.addonsWhere(command, w);
          w.orderByOf(orderByClause);
        }).stream().map(m -> modelMapper.map(m, RoleVo.class))
        .toList();
      return HttpResult.okBody(pageList);

    }
  }

  protected void addonsWhere(QueryRoleCommand command, LambdaQueryChainWrapper<RoleEntity> w) {

    w.eq(RoleEntity::getType, command.getRoleType());

    if (StringUtils.hasText(command.getAppId())) {
      w.eq(RoleEntity::getAppId, command.getAppId());
    } else {
      RoleQuery query = command.getQuery();
      if (StringUtils.hasText(query.getAppId())) {
        w.eq(RoleEntity::getAppId, query.getAppId());
      }
    }

    if (StringUtils.hasText(command.getTenantId())) {
      w.eq(RoleEntity::getAppId, command.getTenantId());
    } else {
      RoleQuery query = command.getQuery();
      if (StringUtils.hasText(query.getTenantId())) {
        w.eq(RoleEntity::getTenantId, query.getTenantId());
      } else {
        w.isNull(RoleEntity::getTenantId);
      }
    }
  }

}
