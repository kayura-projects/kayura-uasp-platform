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
import org.kayura.type.HttpResult;
import org.kayura.uasp.basic.cmd.DeleteNoticeCommand;
import org.kayura.uasp.basic.manage.NoticeManager;
import org.kayura.uasp.common.IdPayload;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeleteNoticeCommandHandler implements CommandHandler<DeleteNoticeCommand, HttpResult> {

  private final NoticeManager noticeManager;

  public DeleteNoticeCommandHandler(NoticeManager noticeManager) {
    this.noticeManager = noticeManager;
  }

  @Override
  public HttpResult execute(DeleteNoticeCommand command) {

    IdPayload payload = Optional.ofNullable(command.getPayload()).orElse(IdPayload.create());
    String countId = Optional.ofNullable(command.getNoticeId()).orElse(payload.getId());

    if (StringUtils.hasText(countId)) {
      noticeManager.deleteById(countId);
    } else if (CollectionUtils.isNotEmpty(payload.getIds())) {
      noticeManager.deleteByIds(payload.getIds());
    }

    return HttpResult.ok();
  }
}
