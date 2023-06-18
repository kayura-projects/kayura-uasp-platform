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
import org.kayura.type.Result;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.organize.OrganizeTypes;
import org.kayura.uasp.organize.cmd.DeleteOrganizeCommand;
import org.kayura.uasp.organize.manage.CompanyManager;
import org.kayura.uasp.organize.manage.DepartManager;
import org.kayura.uasp.organize.manage.PositionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DeleteOrganizeCommandHandler implements CommandHandler<DeleteOrganizeCommand, HttpResult> {

  private final CompanyManager companyManager;
  private final DepartManager departManager;
  private final PositionManager positionManager;

  public DeleteOrganizeCommandHandler(CompanyManager companyManager,
                                      DepartManager departManager,
                                      PositionManager positionManager) {
    this.companyManager = companyManager;
    this.departManager = departManager;
    this.positionManager = positionManager;
  }

  @Transactional
  public HttpResult execute(DeleteOrganizeCommand command) {

    LoginUser loginUser = command.getLoginUser();
    OrganizeTypes type = command.getType();
    IdPayload payload = Optional.ofNullable(command.getPayload())
      .orElse(IdPayload.create().setId(command.getOrgId()));

    Result result;
    if (OrganizeTypes.Company.equals(type)) {
      result = companyManager.deleteForPayload(payload);
    } else if (OrganizeTypes.Depart.equals(type)) {
      result = departManager.deleteForPayload(payload);
    } else {
      result = positionManager.deleteForPayload(payload);
    }
    return HttpResult.of(result);
  }


}
