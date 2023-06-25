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

package org.kayura.uasp.basic.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.basic.cmd.QueryFeedbackCommand;
import org.kayura.uasp.basic.entity.FeedbackEntity;
import org.kayura.uasp.basic.manage.FeedbackManager;
import org.kayura.uasp.feedback.FeedbackQuery;
import org.kayura.uasp.feedback.FeedbackVo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class QueryFeedbackCommandHandler implements CommandHandler<QueryFeedbackCommand, HttpResult> {

  private final FeedbackManager feedbackManager;
  private final ModelMapper modelMapper;

  public QueryFeedbackCommandHandler(FeedbackManager feedbackManager,
                                     ModelMapper modelMapper) {
    this.feedbackManager = feedbackManager;
    this.modelMapper = modelMapper;
  }

  @Override
  public HttpResult execute(QueryFeedbackCommand command) {

    LoginUser loginUser = command.getLoginUser();
    String appId = Optional.ofNullable(command.getAppId()).orElse(loginUser.getAppId());
    FeedbackQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    PageList<FeedbackVo> pageList = feedbackManager.selectPage(w -> {
      w.of(query);
      w.eq(FeedbackEntity::getAppId, appId);
      w.isNull(FeedbackEntity::getSubjectId);
    }, pageClause).streamMap(m -> modelMapper.map(m, FeedbackVo.class));

    return HttpResult.okBody(pageList);
  }
}
