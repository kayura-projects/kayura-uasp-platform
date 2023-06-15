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

package org.kayura.mybatis.mapper.segments;

import org.kayura.mybatis.mapper.wrapper.SqlSegment;
import org.kayura.mybatis.toolkit.StringPool;

public enum SqlKeyword implements SqlSegment {

  AND("AND"),
  OR("OR"),
  IN("IN"),
  NOT("NOT"),
  LIKE("LIKE"),
  EQ(StringPool.EQUALS),
  NE("<>"),
  GT(StringPool.RIGHT_CHEV),
  GE(">="),
  LT(StringPool.LEFT_CHEV),
  LE("<="),
  DISTINCT("DISTINCT"),
  IS_NULL("IS NULL"),
  IS_NOT_NULL("IS NOT NULL"),
  GROUP_BY("GROUP BY"),
  HAVING("HAVING"),
  ORDER_BY("ORDER BY"),
  EXISTS("EXISTS"),
  BETWEEN("BETWEEN"),
  ASC("ASC"),
  DESC("DESC"),

  MAX("MAX"),
  MIN("MIN"),
  COUNT("COUNT"),
  AVG("AVG"),
  SUM("SUM");

  private final String keyword;

  SqlKeyword(final String keyword) {
    this.keyword = keyword;
  }

  @Override
  public String getSqlSegment() {
    return this.keyword;
  }
}
