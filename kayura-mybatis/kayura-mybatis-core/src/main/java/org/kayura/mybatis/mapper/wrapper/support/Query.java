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
