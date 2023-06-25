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
