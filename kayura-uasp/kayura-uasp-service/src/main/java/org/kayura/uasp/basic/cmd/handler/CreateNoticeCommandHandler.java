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
