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
import org.kayura.uasp.basic.cmd.GetNoticeCommand;
import org.kayura.uasp.basic.entity.NoticeEntity;
import org.kayura.uasp.basic.manage.NoticeManager;
import org.kayura.uasp.file.FileLinkVo;
import org.kayura.uasp.file.manage.FileLinkManager;
import org.kayura.uasp.notice.NoticeVo;
import org.kayura.utils.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetNoticeCommandHandler implements CommandHandler<GetNoticeCommand, HttpResult> {

  private final NoticeManager noticeManager;
  private final ModelMapper modelMapper;
  private final FileLinkManager fileLinkManager;

  public GetNoticeCommandHandler(NoticeManager noticeManager,
                                 ModelMapper modelMapper,
                                 FileLinkManager fileLinkManager) {
    this.noticeManager = noticeManager;
    this.modelMapper = modelMapper;
    this.fileLinkManager = fileLinkManager;
  }

  @Override
  public HttpResult execute(GetNoticeCommand command) {

    String noticeId = command.getNoticeId();

    NoticeEntity entity = noticeManager.selectById(noticeId);
    if (entity == null) {
      return HttpResult.error("查找的模块记录不存在。");
    }

    NoticeVo model = modelMapper.map(entity, NoticeVo.class);

    StringList attachmentIds = entity.getAttachmentIds();
    if (CollectionUtils.isNotEmpty(attachmentIds)) {
      List<FileLinkVo> attachments = fileLinkManager.queryForIds(attachmentIds);
      model.setAttachments(attachments);
    }

    return HttpResult.okBody(model);
  }

}
