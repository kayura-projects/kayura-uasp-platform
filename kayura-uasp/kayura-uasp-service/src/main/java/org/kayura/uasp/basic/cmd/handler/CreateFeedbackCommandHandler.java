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
import org.kayura.uasp.basic.cmd.CreateFeedbackCommand;
import org.kayura.uasp.basic.entity.FeedbackEntity;
import org.kayura.uasp.basic.manage.FeedbackManager;
import org.kayura.uasp.feedback.FeedbackPayload;
import org.kayura.uasp.feedback.FeedbackReplyVo;
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
      .setContent(payload.getContent())
      .setAttachmentIds(payload.getAttachmentIds())
      .setAuthorId(loginUser.getUserId())
      .setPostTime(LocalDateTime.now());

    // appId
    if (loginUser.hasTenantUser()) {
      entity.setAppId(loginUser.getAppId());
    } else {
      entity.setAppId(Optional.ofNullable(payload.getAppId()).orElse(loginUser.getAppId()));
    }

    if (StringUtils.isBlank(subjectId)) {
      entity.setFlag(1);
      entity.setSubjectId(entity.getPostId());
      entity.setTitle(payload.getTitle());
      entity.setCategory(payload.getCategory());
      entity.setSolved(Boolean.FALSE);
      entity.setStatus(PostStatus.Valid);
    } else {
      FeedbackEntity subject = feedbackManager.selectById(subjectId);
      if (subject == null) {
        return HttpResult.error("要回复的反馈不存在。");
      }
      if (PostStatus.Closed.equals(subject.getStatus())) {
        return HttpResult.error("要回复的反馈已经关闭。");
      }
      entity.setFlag(0);
      entity.setSubjectId(subjectId);
    }

    feedbackManager.insertOne(entity);

    return HttpResult.okBody(
      FeedbackReplyVo.create()
        .setPostId(entity.getPostId())
        .setContent(entity.getContent())
        .setAuthorId(entity.getAuthorId())
        .setAuthorName(loginUser.getDisplayName())
        .setPostTime(entity.getPostTime())
    );
  }

}
