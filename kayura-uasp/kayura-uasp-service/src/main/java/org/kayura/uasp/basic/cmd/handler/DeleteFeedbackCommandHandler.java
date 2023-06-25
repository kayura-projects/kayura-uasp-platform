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
import org.kayura.uasp.basic.cmd.DeleteFeedbackCommand;
import org.kayura.uasp.basic.entity.FeedbackEntity;
import org.kayura.uasp.basic.manage.FeedbackManager;
import org.kayura.uasp.common.IdPayload;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteFeedbackCommandHandler implements CommandHandler<DeleteFeedbackCommand, HttpResult> {

  private final FeedbackManager feedbackManager;

  public DeleteFeedbackCommandHandler(FeedbackManager feedbackManager) {
    this.feedbackManager = feedbackManager;
  }

  @Override
  public HttpResult execute(DeleteFeedbackCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdPayload payload = command.getPayload();
    String deleteType = command.getDeleteType();

    List<FeedbackEntity> entities = feedbackManager.selectList(w -> {
      if (CollectionUtils.isNotEmpty(payload.getIds())) {
        w.in(FeedbackEntity::getPostId, payload.getIds());
      } else if (StringUtils.isNotBlank(payload.getId())) {
        w.eq(FeedbackEntity::getPostId, payload.getId());
      }
    });

    for (FeedbackEntity entity : entities) {
      if (entity.getSubjectId().equals(entity.getPostId())) {
      }
    }


    return null;
  }
}
