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
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.basic.cmd.QueryNoticeCommand;
import org.kayura.uasp.basic.manage.NoticeManager;
import org.kayura.uasp.notice.NoticeQuery;
import org.kayura.uasp.notice.NoticeVo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class QueryNoticeCommandHandler implements CommandHandler<QueryNoticeCommand, HttpResult> {

  private final NoticeManager noticeManager;
  private final ModelMapper modelMapper;

  public QueryNoticeCommandHandler(NoticeManager noticeManager,
                                   ModelMapper modelMapper) {
    this.noticeManager = noticeManager;
    this.modelMapper = modelMapper;
  }

  @Override
  public HttpResult execute(QueryNoticeCommand command) {

    NoticeQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    PageList<NoticeVo> pageList = noticeManager.selectPage(w -> {
      w.of(query);
    }, pageClause).streamMap(m -> modelMapper.map(m, NoticeVo.class));

    return HttpResult.okBody(pageList);
  }

}

