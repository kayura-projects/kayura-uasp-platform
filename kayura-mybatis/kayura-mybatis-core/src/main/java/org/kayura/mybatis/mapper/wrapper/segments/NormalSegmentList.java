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

package org.kayura.mybatis.mapper.wrapper.segments;

import org.kayura.mybatis.mapper.segments.SqlKeyword;
import org.kayura.mybatis.mapper.wrapper.SqlSegment;

import java.util.List;
import java.util.stream.Collectors;

public class NormalSegmentList extends AbstractSqlSegmentList {

  private boolean executeNot = true;

  public NormalSegmentList() {
    this.flushLastValue = true;
  }

  @Override
  protected boolean transformList(List<SqlSegment> list, SqlSegment firstSegment) {
    if (list.size() == 1) {
      if (!MatchSegment.NOT.match(firstSegment)) {
        if (isEmpty()) {
          return false;
        }
        boolean matchLastAnd = MatchSegment.AND.match(lastValue);
        boolean matchLastOr = MatchSegment.OR.match(lastValue);
        if (matchLastAnd || matchLastOr) {
          if (matchLastAnd && MatchSegment.AND.match(firstSegment)) {
            return false;
          } else if (matchLastOr && MatchSegment.OR.match(firstSegment)) {
            return false;
          } else {
            removeFlushLast();
          }
        }
      } else {
        executeNot = false;
        return false;
      }
    } else {
      if (!executeNot) {
        list.add(MatchSegment.EXISTS.match(firstSegment) ? 0 : 1, SqlKeyword.NOT);
        executeNot = true;
      }
      if (!MatchSegment.AND_OR.match(lastValue) && !isEmpty()) {
        add(SqlKeyword.AND);
      }
      if (MatchSegment.APPLY.match(firstSegment)) {
        list.remove(0);
      }
    }
    return true;
  }

  @Override
  protected String childrenSqlSegment() {
    if (MatchSegment.AND_OR.match(lastValue)) {
      removeFlushLast();
    }
    return this.stream().map(SqlSegment::getSqlSegment).collect(Collectors.joining(SPACE));
  }

}
