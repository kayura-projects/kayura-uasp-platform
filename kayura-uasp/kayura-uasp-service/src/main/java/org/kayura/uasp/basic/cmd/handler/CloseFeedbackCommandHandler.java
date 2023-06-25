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
import org.kayura.uasp.basic.cmd.CloseFeedbackCommand;
import org.kayura.uasp.basic.entity.FeedbackEntity;
import org.kayura.uasp.basic.manage.FeedbackManager;
import org.kayura.uasp.feedback.PostStatus;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CloseFeedbackCommandHandler implements CommandHandler<CloseFeedbackCommand, HttpResult> {

  private final FeedbackManager feedbackManager;

  public CloseFeedbackCommandHandler(FeedbackManager feedbackManager) {
    this.feedbackManager = feedbackManager;
  }

  @Override
  public HttpResult execute(CloseFeedbackCommand command) {

    LoginUser loginUser = command.getLoginUser();
    String postId = command.getPostId();
    Boolean solved = command.getSolved();

    FeedbackEntity entity = feedbackManager.selectById(postId);
    if (entity == null || StringUtils.isBlank(entity.getSubjectId())) {
      return HttpResult.error("问题反馈记录不存在。");
    }
    if (PostStatus.Closed.equals(entity.getStatus())) {
      return HttpResult.error("此问题反馈已经关闭。");
    }
    if (!loginUser.getUserId().equals(entity.getAuthorId())) {
      return HttpResult.error("仅允许关闭自己的问题反馈。");
    }

    feedbackManager.updateByWhere(w -> {
      w.set(FeedbackEntity::getSolved, Boolean.TRUE.equals(solved));
      w.eq(FeedbackEntity::getPostId, postId);
    });

    return HttpResult.ok();
  }

}
