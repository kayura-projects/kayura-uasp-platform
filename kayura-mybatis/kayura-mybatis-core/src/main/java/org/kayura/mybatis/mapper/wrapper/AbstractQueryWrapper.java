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

package org.kayura.mybatis.mapper.wrapper;

import org.kayura.mybatis.mapper.metadata.ColumnInfo;
import org.kayura.mybatis.mapper.segments.SqlKeyword;
import org.kayura.mybatis.mapper.wrapper.segments.*;
import org.kayura.mybatis.mapper.wrapper.support.Query;
import org.kayura.mybatis.mapper.wrapper.support.SharedString;
import org.kayura.mybatis.toolkit.StringKit;
import org.kayura.mybatis.toolkit.StringPool;
import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.OrderByClause;
import org.kayura.type.OrderByItem;
import org.kayura.type.PageClause;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.kayura.mybatis.mapper.segments.SqlKeyword.*;

@SuppressWarnings({"unchecked"})
public abstract class AbstractQueryWrapper<R> extends AbstractWrapper<AbstractQueryWrapper<R>, R>
  implements Query<AbstractQueryWrapper<R>, R>, StringPool {

  private static final Log LOGGER = LogFactory.getLog(AbstractQueryWrapper.class);

  protected final SharedString firstSql = SharedString.emptyString();
  protected final SharedString lastSql = SharedString.emptyString();
  protected final SelectSegmentList selects = new SelectSegmentList();
  protected final GroupBySegmentList groupBy = new GroupBySegmentList();
  protected final OrderBySegmentList orderBy = new OrderBySegmentList();
  protected final HavingSegmentList having = new HavingSegmentList();

  public AbstractQueryWrapper(Class<?> entityClass) {
    super(entityClass);
    super.initNeed();
  }

  public String getSqlFirst() {
    return this.firstSql.getStringValue();
  }

  public String getSqlLast() {
    return this.lastSql.getStringValue();
  }

  public String getDistinct() {
    return this.isDistinct ? DISTINCT.getSqlSegment() : "";
  }

  public String getSqlSelect() {
    return selects.getSqlSegment();
  }

  public String getSqlGroupBy() {
    return groupBy.getSqlSegment();
  }

  public String getSqlHaving() {
    return having.getSqlSegment();
  }

  public String getSqlOrderBy() {
    return orderBy.getSqlSegment();
  }

  public AbstractQueryWrapper<R> first(boolean condition, String firstSql) {
    if (condition) {
      this.firstSql.setStringValue(StringPool.SPACE + firstSql);
    }
    return typedThis;
  }

  public AbstractQueryWrapper<R> of(Object queryObject, String orderByRequest) {
    return this.of(queryObject, OrderByClause.resolveForNative(orderByRequest));
  }

  public AbstractQueryWrapper<R> of(Object queryObject, OrderByClause orderByClause) {
    this.of(queryObject);
    this.orderByOf(orderByClause);
    return typedThis;
  }

  public AbstractQueryWrapper<R> orderByOf(OrderByClause orderByClause) {
    if (orderByClause != null) {
      for (OrderByItem item : orderByClause) {
        if (columnMap.containsKey(item.getOrder())) {
          doFunc(true, ORDER_BY, () -> columnNameToFieldName(item.getOrder()), item.hasAsc() ? ASC : DESC);
        } else {
          LOGGER.warn("未能识别的排序字段 " + item.getOrder() + "，将被忽略。");
        }
      }
    }
    return typedThis;
  }

  @Override
  public AbstractQueryWrapper<R> first(String lastSql) {
    return first(true, lastSql);
  }

  @Override
  public AbstractQueryWrapper<R> last(boolean condition, String lastSql) {
    if (condition) {
      this.lastSql.setStringValue(StringPool.SPACE + lastSql);
    }
    return typedThis;
  }

  @Override
  public AbstractQueryWrapper<R> last(String lastSql) {
    return last(true, lastSql);
  }

  @Override
  @SafeVarargs
  public final AbstractQueryWrapper<R> select(R... columns) {
    for (R column : columns) {
      doSelect(true, () -> columnToSelectName(column));
    }
    return typedThis;
  }

  @Override
  public AbstractQueryWrapper<R> select(Predicate<ColumnInfo> predicate) {
    List<String> columns = entityInfo.chooseSelect(predicate);
    for (String column : columns) {
      doSelect(true, () -> columnNameToSelectName(column));
    }
    return typedThis;
  }

  public AbstractQueryWrapper<R> distinct() {
    this.isDistinct = true;
    return typedThis;
  }

  protected AggSqlSegment<R> aggFuncInstance(String funcName) {
    return new AggSqlSegment<>(funcName, this);
  }

  protected AbstractQueryWrapper<R> doAggFunc(String funcName, Function<AggSqlSegment<R>, AggSqlSegment<R>> func) {
    return doSelect(true, func.apply(aggFuncInstance(funcName)));
  }

  public AbstractQueryWrapper<R> max(Function<AggSqlSegment<R>, AggSqlSegment<R>> func) {
    return doAggFunc(MAX.getSqlSegment(), func);
  }

  public AbstractQueryWrapper<R> min(Function<AggSqlSegment<R>, AggSqlSegment<R>> func) {
    return doAggFunc(MIN.getSqlSegment(), func);
  }

  public AbstractQueryWrapper<R> sum(Function<AggSqlSegment<R>, AggSqlSegment<R>> func) {
    return doAggFunc(SUM.getSqlSegment(), func);
  }

  public AbstractQueryWrapper<R> avg(Function<AggSqlSegment<R>, AggSqlSegment<R>> func) {
    return doAggFunc(AVG.getSqlSegment(), func);
  }

  public AbstractQueryWrapper<R> count(Function<AggSqlSegment<R>, AggSqlSegment<R>> func) {
    return doAggFunc(COUNT.getSqlSegment(), func);
  }

  @Override
  public AbstractQueryWrapper<R> groupBy(boolean condition, R... columns) {
    for (R column : columns) {
      doFunc(true, GROUP_BY, () -> columnToFieldName(column));
    }
    return typedThis;
  }

  @Override
  public AbstractQueryWrapper<R> groupBy(R... columns) {
    return groupBy(true, columns);
  }

  @Override
  public AbstractQueryWrapper<R> orderBy(boolean condition, boolean isAsc, R... columns) {
    SqlKeyword mode = isAsc ? ASC : DESC;
    for (R column : columns) {
      doFunc(true, ORDER_BY, () -> columnToFieldName(column), mode);
    }
    return typedThis;
  }

  public PageBounds pageBounds(int page, int limit) {
    return pageBounds(page, limit, null);
  }

  public PageBounds pageBounds(int page, int limit, OrderByClause orderByClause) {
    PageBounds pageBounds = new PageBounds(page, limit);
    if (orderByClause != null && !orderByClause.isEmpty()) {
      pageBounds.setOrderBy(resolveNativeOrderBy(orderByClause));
    } else {
      String orderBySql = orderBy.getSqlSegment();
      if (StringKit.isNotBlank(orderBySql)) {
        pageBounds.setOrderBy(orderBySql);
      } else if (!entityInfo.getSorts().isEmpty()) {
        String defaultOrderBy = entityInfo.defaultOrderBy();
        pageBounds.setOrderBy(defaultOrderBy);
      }
    }
    return pageBounds;
  }

  public PageBounds pageBounds(PageClause pageClause) {
    return pageBounds(pageClause.getPage(), pageClause.getRows(), pageClause.getOrderby());
  }

  protected String resolveNativeOrderBy(OrderByClause orderByItems) {
    OrderByClause orderByClause = new OrderByClause();
    for (OrderByItem item : orderByItems) {
      if (this.columnMap.containsKey(item.getOrder())) {
        String order = columnNameToFieldName(item.getOrder());
        String sort = item.getDirection();
        orderByClause.add(new OrderByItem(order, sort));
      }
    }
    return orderByClause.genNativeSql();
  }

  @Override
  public AbstractQueryWrapper<R> orderByAsc(boolean condition, R... columns) {
    return orderBy(condition, true, columns);
  }

  @Override
  public AbstractQueryWrapper<R> orderByAsc(R... columns) {
    return orderByAsc(true, columns);
  }

  @Override
  public AbstractQueryWrapper<R> orderByAsc(R column) {
    return orderByAsc(true, column);
  }

  @Override
  public AbstractQueryWrapper<R> orderByDesc(boolean condition, R... columns) {
    return orderBy(condition, false, columns);
  }

  @Override
  public AbstractQueryWrapper<R> orderByDesc(R... columns) {
    return orderByDesc(true, columns);
  }

  @Override
  public AbstractQueryWrapper<R> orderByDesc(R column) {
    return orderByDesc(true, column);
  }

  @Override
  public AbstractQueryWrapper<R> having(boolean condition, String sqlHaving, Object... params) {
    return doFunc(condition, HAVING, () -> formatSqlIfNeed(condition, sqlHaving, params));
  }

  @Override
  public AbstractQueryWrapper<R> having(String sqlHaving, Object... params) {
    return having(true, sqlHaving, params);
  }

  protected AbstractQueryWrapper<R> doSelect(boolean condition, SqlSegment... sqlSegments) {
    if (condition) {
      selects.addAll(Arrays.asList(sqlSegments));
    }
    return typedThis;
  }

  protected AbstractQueryWrapper<R> doFunc(boolean condition, SqlSegment... sqlSegments) {
    if (condition) {
      List<SqlSegment> list = Arrays.asList(sqlSegments);
      SqlSegment firstSqlSegment = list.get(0);
      if (MatchSegment.ORDER_BY.match(firstSqlSegment)) {
        orderBy.addAll(list);
      } else if (MatchSegment.GROUP_BY.match(firstSqlSegment)) {
        groupBy.addAll(list);
      } else if (MatchSegment.HAVING.match(firstSqlSegment)) {
        having.addAll(list);
      }
    }
    return typedThis;
  }

}
