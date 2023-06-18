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
