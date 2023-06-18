/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
