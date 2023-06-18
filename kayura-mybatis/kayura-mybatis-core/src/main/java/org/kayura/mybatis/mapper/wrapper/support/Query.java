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

package org.kayura.mybatis.mapper.wrapper.support;

import org.kayura.mybatis.mapper.metadata.ColumnInfo;
import org.kayura.mybatis.mapper.wrapper.segments.AggSqlSegment;

import java.io.Serializable;
import java.util.function.Function;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public interface Query<Children, R> extends Serializable {

  Children select(R... columns);

  Children select(Predicate<ColumnInfo> predicate);

  String getSqlFirst();

  String getDistinct();

  String getSqlSelect();

  String getSqlGroupBy();

  String getSqlOrderBy();

  String getSqlLast();

  Children first(boolean condition, String firstSql);

  Children first(String lastSql);


  Children last(boolean condition, String lastSql);

  Children last(String lastSql);

  Children max(Function<AggSqlSegment<R>, AggSqlSegment<R>> func);

  Children min(Function<AggSqlSegment<R>, AggSqlSegment<R>> func);

  Children sum(Function<AggSqlSegment<R>, AggSqlSegment<R>> func);

  Children avg(Function<AggSqlSegment<R>, AggSqlSegment<R>> func);

  Children count(Function<AggSqlSegment<R>, AggSqlSegment<R>> func);

  Children groupBy(boolean condition, R... columns);

  Children groupBy(R... columns);


  Children orderBy(boolean condition, boolean isAsc, R... columns);

  Children orderByAsc(boolean condition, R... columns);

  Children orderByAsc(R... columns);

  Children orderByAsc(R column);

  Children orderByDesc(boolean condition, R... columns);

  Children orderByDesc(R... columns);

  Children orderByDesc(R column);


  Children having(boolean condition, String sqlHaving, Object... params);

  Children having(String sqlHaving, Object... params);
}
