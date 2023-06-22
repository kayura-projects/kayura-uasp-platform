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
import org.kayura.uasp.basic.cmd.UpdateNoticeCommand;
import org.kayura.uasp.basic.entity.NoticeEntity;
import org.kayura.uasp.basic.manage.NoticeManager;
import org.kayura.uasp.notice.NoticePayload;
import org.kayura.uasp.utils.UaspConsts;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class UpdateNoticeCommandHandler implements CommandHandler<UpdateNoticeCommand, HttpResult>, UaspConsts {

  private final NoticeManager noticeManager;

  public UpdateNoticeCommandHandler(NoticeManager noticeManager) {
    this.noticeManager = noticeManager;
  }

  @Override
  public HttpResult execute(UpdateNoticeCommand command) {

    LoginUser loginUser = command.getLoginUser();
    NoticePayload payload = command.getPayload();
    String noticeId = Optional.ofNullable(command.getNoticeId()).orElse(payload.getNoticeId());

    NoticeEntity entity = noticeManager.selectById(noticeId);
    if (entity == null) {
      return HttpResult.error(UPDATE_ENTITY_NOT_EXISTS);
    }
    entity.setTitle(payload.getTitle());
    entity.setContent(payload.getContent());
    entity.setStatus(payload.getStatus());
    entity.setUpdaterId(loginUser.getUserId());
    entity.setUpdateTime(LocalDateTime.now());
    entity.setExpireDay(payload.getExpireDay());
    if (DataStatus.Valid.equals(payload.getStatus())) {
      entity.setReleaseTime(LocalDateTime.now());
    } else {
      entity.setReleaseTime(null);
    }
    noticeManager.updateById(noticeId, entity);

    return HttpResult.ok();
  }

}
