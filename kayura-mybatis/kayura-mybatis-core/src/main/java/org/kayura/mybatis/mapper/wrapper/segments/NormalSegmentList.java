/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ 版权所属 2019 Liang.Xia 及 原作者
 ~ 您可以在 Apache License 2.0 版下获得许可副本，
 ~ 同时必须保证分发的本软件是在“原始”的基础上分发的。
 ~ 除非适用法律要求或书面同意。
 ~
 ~ http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ 请参阅许可证中控制权限和限制的特定语言。
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

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
