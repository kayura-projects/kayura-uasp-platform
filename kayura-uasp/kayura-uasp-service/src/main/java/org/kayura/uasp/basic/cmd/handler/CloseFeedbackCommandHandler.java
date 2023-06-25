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
