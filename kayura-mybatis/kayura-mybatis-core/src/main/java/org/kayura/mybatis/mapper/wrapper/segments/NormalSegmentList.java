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
