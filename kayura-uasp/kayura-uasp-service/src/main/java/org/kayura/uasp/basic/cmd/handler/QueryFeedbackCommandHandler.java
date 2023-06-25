/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
