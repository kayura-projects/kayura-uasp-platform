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
