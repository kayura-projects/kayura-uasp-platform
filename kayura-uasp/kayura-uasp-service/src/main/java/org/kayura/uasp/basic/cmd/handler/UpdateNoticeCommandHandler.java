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
    entity.setAttachmentIds(payload.getAttachmentIds());
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
