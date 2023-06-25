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
import org.kayura.uasp.basic.cmd.CreateFeedbackCommand;
import org.kayura.uasp.basic.entity.FeedbackEntity;
import org.kayura.uasp.basic.manage.FeedbackManager;
import org.kayura.uasp.feedback.FeedbackPayload;
import org.kayura.uasp.feedback.PostStatus;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class CreateFeedbackCommandHandler implements CommandHandler<CreateFeedbackCommand, HttpResult> {

  private final FeedbackManager feedbackManager;

  public CreateFeedbackCommandHandler(FeedbackManager feedbackManager) {
    this.feedbackManager = feedbackManager;
  }

  @Override
  public HttpResult execute(CreateFeedbackCommand command) {

    LoginUser loginUser = command.getLoginUser();
    FeedbackPayload payload = command.getPayload();
    String subjectId = payload.getSubjectId();

    FeedbackEntity entity = FeedbackEntity.create()
      .setPostId(feedbackManager.nextId())
      .setAppId(loginUser.getAppId())
      .setContent(payload.getContent())
      .setAttachmentIds(payload.getAttachmentIds())
      .setAuthorId(loginUser.getUserId())
      .setPostTime(LocalDateTime.now());

    if (StringUtils.isBlank(subjectId)) {
      entity.setTitle(payload.getTitle());
      entity.setCategory(payload.getCategory());
      entity.setSolved(Boolean.FALSE);
      entity.setStatus(Optional.ofNullable(payload.getStatus()).orElse(PostStatus.Draft));
    } else {
      FeedbackEntity subject = feedbackManager.selectById(subjectId);
      if (subject == null || PostStatus.Draft.equals(subject.getStatus())) {
        return HttpResult.error("要回复的反馈不存在。");
      }
      if (PostStatus.Closed.equals(subject.getStatus())) {
        return HttpResult.error("要回复的反馈已经关闭。");
      }
      entity.setSubjectId(subjectId);
    }

    feedbackManager.insertOne(entity);

    return HttpResult.ok();
  }

}
