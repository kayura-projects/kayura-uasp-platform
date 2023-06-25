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
import org.kayura.type.DataStatus;
import org.kayura.type.HttpResult;
import org.kayura.uasp.basic.cmd.CreateNoticeCommand;
import org.kayura.uasp.basic.entity.NoticeEntity;
import org.kayura.uasp.basic.manage.NoticeManager;
import org.kayura.uasp.notice.NoticePayload;
import org.kayura.uasp.notice.NoticeVo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class CreateNoticeCommandHandler implements CommandHandler<CreateNoticeCommand, HttpResult> {

  private final NoticeManager noticeManager;
  private final ModelMapper modelMapper;

  public CreateNoticeCommandHandler(NoticeManager noticeManager,
                                    ModelMapper modelMapper) {
    this.noticeManager = noticeManager;
    this.modelMapper = modelMapper;
  }

  @Override
  public HttpResult execute(CreateNoticeCommand command) {

    LoginUser loginUser = command.getLoginUser();
    NoticePayload payload = command.getPayload();

    NoticeEntity entity = NoticeEntity.create();
    entity.setNoticeId(Optional.ofNullable(payload.getNoticeId()).orElse(noticeManager.nextId()));
    entity.setAppId(payload.getAppId());
    entity.setTenantId(payload.getTenantId());
    entity.setType(payload.getType());
    entity.setTitle(payload.getTitle());
    entity.setContent(payload.getContent());
    entity.setAttachmentIds(payload.getAttachmentIds());
    entity.setCreatorId(loginUser.getUserId());
    entity.setCreateTime(LocalDateTime.now());
    entity.setExpireDay(payload.getExpireDay());
    if (DataStatus.Valid.equals(payload.getStatus())) {
      entity.setReleaseTime(LocalDateTime.now());
    }
    entity.setStatus(Optional.ofNullable(payload.getStatus()).orElse(DataStatus.Draft));
    noticeManager.insertOne(entity);

    NoticeVo model = modelMapper.map(entity, NoticeVo.class);
    return HttpResult.okBody(model);
  }

}
