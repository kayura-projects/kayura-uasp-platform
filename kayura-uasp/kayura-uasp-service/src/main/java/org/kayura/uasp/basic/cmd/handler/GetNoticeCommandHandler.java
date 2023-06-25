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
