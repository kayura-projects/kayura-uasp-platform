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

package org.kayura.uasp.ops.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.applibrary.AppStoreQuery;
import org.kayura.uasp.applibrary.AppStoreVo;
import org.kayura.uasp.ops.cmd.QueryAppStoreCommand;
import org.kayura.uasp.ops.manage.AppStoreManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class QueryAppStoreCommandHandler implements CommandHandler<QueryAppStoreCommand, HttpResult> {

  private final ModelMapper modelMapper;
  private final AppStoreManager appStoreManager;

  public QueryAppStoreCommandHandler(ModelMapper modelMapper,
                                     AppStoreManager appStoreManager) {
    this.modelMapper = modelMapper;
    this.appStoreManager = appStoreManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryAppStoreCommand command) {

    LoginUser loginUser = command.getLoginUser();
    PageClause pageClause = command.getPageClause();
    AppStoreQuery query = command.getQuery();

    PageList<AppStoreVo> pageList = appStoreManager.selectPage(w -> {
      w.of(query);
    }, pageClause).streamMap(m -> modelMapper.map(m, AppStoreVo.class));
    return HttpResult.okBody(pageList);
  }
}
