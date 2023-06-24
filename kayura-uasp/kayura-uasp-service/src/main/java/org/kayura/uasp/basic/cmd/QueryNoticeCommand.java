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

package org.kayura.uasp.basic.cmd;

import org.kayura.cmd.ApiCommand;
import org.kayura.type.PageClause;
import org.kayura.uasp.notice.NoticeQuery;

public class QueryNoticeCommand extends ApiCommand {

  private NoticeQuery query;
  private PageClause pageClause;

  public NoticeQuery getQuery() {
    return query;
  }

  public QueryNoticeCommand setQuery(NoticeQuery query) {
    this.query = query;
    return this;
  }

  public PageClause getPageClause() {
    return pageClause;
  }

  public QueryNoticeCommand setPageClause(PageClause pageClause) {
    this.pageClause = pageClause;
    return this;
  }
}