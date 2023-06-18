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
