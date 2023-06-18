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
import org.kayura.mybatis.mapper.wrapper.support.WrapperKeyword;

import java.util.function.Predicate;

public enum MatchSegment {

  GROUP_BY(i -> i == SqlKeyword.GROUP_BY),

  ORDER_BY(i -> i == SqlKeyword.ORDER_BY),

  NOT(i -> i == SqlKeyword.NOT),

  AND(i -> i == SqlKeyword.AND),

  OR(i -> i == SqlKeyword.OR),

  AND_OR(i -> i == SqlKeyword.AND || i == SqlKeyword.OR),

  EXISTS(i -> i == SqlKeyword.EXISTS),

  HAVING(i -> i == SqlKeyword.HAVING),

  APPLY(i -> i == WrapperKeyword.APPLY);

  private final Predicate<SqlSegment> predicate;

  MatchSegment(Predicate<SqlSegment> predicate) {
    this.predicate = predicate;
  }

  public boolean match(SqlSegment segment) {
    return getPredicate().test(segment);
  }

  protected Predicate<SqlSegment> getPredicate() {
    return predicate;
  }
}
