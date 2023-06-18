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
