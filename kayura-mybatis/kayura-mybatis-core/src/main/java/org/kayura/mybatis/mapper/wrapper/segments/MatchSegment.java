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
