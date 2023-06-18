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
