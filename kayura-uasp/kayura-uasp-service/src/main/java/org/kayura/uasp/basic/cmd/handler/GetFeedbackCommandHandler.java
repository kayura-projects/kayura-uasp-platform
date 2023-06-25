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
import org.kayura.type.HttpResult;
import org.kayura.type.StringList;
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

import java.util.List;

import static org.kayura.uasp.utils.TipConsts.*;

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

    FeedbackVo model = modelMapper.map(entity, FeedbackVo.class);

    // attachments
    StringList attachmentIds = entity.getAttachmentIds();
    if (CollectionUtils.isNotEmpty(attachmentIds)) {
      List<FileLinkVo> attachments = fileLinkManager.queryForIds(attachmentIds);
      model.setAttachments(attachments);
    }

    // replays
    List<FeedbackReplyVo> replays = entities.stream()
      .filter(x -> feedbackId.equals(x.getSubjectId()))
      .map(m -> modelMapper.map(m, FeedbackReplyVo.class))
      .toList();
    model.setReplays(replays);

    return HttpResult.okBody(model);
  }
}
