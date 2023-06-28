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
import org.kayura.uasp.basic.cmd.GetFeedbackCommand;
import org.kayura.uasp.basic.entity.FeedbackEntity;
import org.kayura.uasp.basic.manage.FeedbackManager;
import org.kayura.uasp.feedback.FeedbackReplyVo;
import org.kayura.uasp.feedback.FeedbackVo;
import org.kayura.uasp.file.FileLinkVo;
import org.kayura.uasp.file.manage.FileLinkManager;
import org.kayura.utils.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.kayura.uasp.utils.TipConsts.UPDATE_ENTITY_NOT_EXISTS;

@Component
public class GetFeedbackCommandHandler implements CommandHandler<GetFeedbackCommand, HttpResult> {

  private final FeedbackManager feedbackManager;
  private final FileLinkManager fileLinkManager;
  private final ModelMapper modelMapper;

  public GetFeedbackCommandHandler(FeedbackManager feedbackManager,
                                   FileLinkManager fileLinkManager,
                                   ModelMapper modelMapper) {
    this.feedbackManager = feedbackManager;
    this.fileLinkManager = fileLinkManager;
    this.modelMapper = modelMapper;
  }

  @Override
  public HttpResult execute(GetFeedbackCommand command) {

    LoginUser loginUser = command.getLoginUser();
    String feedbackId = command.getFeedbackId();

    List<FeedbackEntity> entities = feedbackManager.selectList(w -> {
      w.eq(FeedbackEntity::getSubjectId, feedbackId);
    });

    // subject
    FeedbackEntity entity = entities.stream()
      .filter(x -> x.getSubjectId().equals(x.getPostId()))
      .findAny().orElse(null);
    if (entity == null) {
      return HttpResult.error(UPDATE_ENTITY_NOT_EXISTS);
    }

    // check
    if (loginUser.hasTenantUser() && !entity.getAuthorId().equals(loginUser.getUserId())) {
      return HttpResult.error("只有创建者与管理员能读取详情。");
    }

    FeedbackVo model = modelMapper.map(entity, FeedbackVo.class);

    // attachments
    List<String> attachmentIds = entities.stream().flatMap(m -> m.getAttachmentIds().stream()).toList();
    List<FileLinkVo> attachments = CollectionUtils.isNotEmpty(attachmentIds)
      ? fileLinkManager.queryForIds(attachmentIds)
      : new ArrayList<>();
    model.setAttachments(attachments.stream().filter(x -> entity.getAttachmentIds().contains(x.getLinkId())).toList());

    // replays
    List<FeedbackReplyVo> replays = entities.stream()
      .filter(x -> !x.getSubjectId().equals(x.getPostId()))
      .map(m -> {
        FeedbackReplyVo mapped = modelMapper.map(m, FeedbackReplyVo.class);
        mapped.setAttachments(attachments.stream().filter(x -> m.getAttachmentIds().contains(x.getLinkId())).toList());
        return mapped;
      })
      .toList();
    model.setReplays(replays);

    return HttpResult.okBody(model);
  }
}
