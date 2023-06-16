/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.OrderByClause;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.organize.IdentityQuery;
import org.kayura.uasp.organize.IdentityVo;
import org.kayura.uasp.organize.cmd.QueryIdentityCommand;
import org.kayura.uasp.organize.manage.IdentityManager;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class QueryIdentityCommandHandler implements CommandHandler<QueryIdentityCommand, HttpResult> {

  private final ModelMapper modelMapper;
  private final IdentityManager identityManager;

  public QueryIdentityCommandHandler(ModelMapper modelMapper,
                                     IdentityManager identityManager) {
    this.modelMapper = modelMapper;
    this.identityManager = identityManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryIdentityCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdentityQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();
    String employeeId = Optional.ofNullable(command.getEmployeeId()).orElse(query.getEmployeeId());

    if (StringUtils.isBlank(employeeId)) {
      return HttpResult.error("必需要指定员工ID条件。");
    }

    if (pageClause != null) {
      PageList<IdentityVo> pageList = identityManager.selectPage(w -> {
        w.of(query);
      }, pageClause).streamMap(m -> modelMapper.map(m, IdentityVo.class));
      return HttpResult.okBody(pageList);
    } else {
      OrderByClause orderByClause = command.getOrderByClause();
      List<IdentityVo> list = identityManager.selectList(w -> {
        w.of(query);
        w.orderByOf(orderByClause);
      }).stream().map(m -> modelMapper.map(m, IdentityVo.class)).toList();
      return HttpResult.okBody(list);
    }
  }

}
