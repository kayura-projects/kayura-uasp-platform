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

package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.PageClause;
import org.kayura.uasp.dict.DictItemQuery;
import org.kayura.uasp.utils.OutputTypes;

public class QueryDictItemCommand extends Command {

  private OutputTypes output;
  private DictItemQuery query;
  private PageClause pageClause;

  public DictItemQuery getQuery() {
    return query;
  }

  public QueryDictItemCommand setQuery(DictItemQuery query) {
    this.query = query;
    return this;
  }

  public PageClause getPageClause() {
    return pageClause;
  }

  public QueryDictItemCommand setPageClause(PageClause pageClause) {
    this.pageClause = pageClause;
    return this;
  }

  public OutputTypes getOutput() {
    return output;
  }

  public QueryDictItemCommand setOutput(OutputTypes output) {
    this.output = output;
    return this;
  }
}
