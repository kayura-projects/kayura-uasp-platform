/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
