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

