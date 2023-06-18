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
