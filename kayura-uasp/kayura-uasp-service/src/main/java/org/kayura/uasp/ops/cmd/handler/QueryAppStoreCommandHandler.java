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
