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

import org.kayura.mybatis.annotation.Ignore;
import org.kayura.mybatis.annotation.querier.Mapping;
import org.kayura.mybatis.mapper.metadata.ColumnMap;
import org.kayura.mybatis.mapper.metadata.EntityHelper;
import org.kayura.mybatis.mapper.metadata.EntityInfo;
import org.kayura.mybatis.mapper.segments.SqlKeyword;
import org.kayura.mybatis.mapper.wrapper.segments.NormalSegmentList;
import org.kayura.mybatis.mapper.wrapper.support.Compare;
import org.kayura.mybatis.mapper.wrapper.support.Getter;
import org.kayura.mybatis.mapper.wrapper.support.Join;
import org.kayura.mybatis.mapper.wrapper.support.Nested;
import org.kayura.mybatis.toolkit.*;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.annotation.AnnotationUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.kayura.mybatis.mapper.segments.SqlKeyword.*;
import static org.kayura.mybatis.mapper.wrapper.support.WrapperKeyword.*;
import static java.util.stream.Collectors.joining;

@SuppressWarnings("unchecked")
public abstract class AbstractWrapper<Children extends AbstractWrapper<Children, R>, R> extends Wrapper
  implements Compare<Children, R>, Nested<Children, AbstractWhereWrapper<R>>, Join<Children> {

  protected final Children typedThis = (Children) this;

  protected boolean isDistinct;
  protected EntityInfo entityInfo;
  protected Class<?> entityClass;
  protected Map<String, ColumnMap> columnMap;

  protected AtomicInteger paramSeq;
  protected Map<String, Object> paramValues;
  protected NormalSegmentList normal;

  public AbstractWrapper(Class<?> entityClass) {
    this.entityClass = entityClass;
    this.entityInfo = EntityHelper.getEntityInfo(entityClass);
    this.columnMap = entityInfo.getColumnMap();
  }

  protected final void initNeed() {
    this.paramSeq = new AtomicInteger(0);
    this.paramValues = new HashMap<>(16);
    this.normal = new NormalSegmentList();
  }

  @Override
  public String getSqlSegment() {
    return normal.getSqlSegment();
  }

  public String getSqlWhere() {
    return normal.getSqlSegment();
  }

  public Map<String, Object> getParamValues() {
    return paramValues;
  }

  @Override
  public <V> Children allEq(boolean condition, Map<R, V> params, boolean null2IsNull) {
    if (condition && CollectionUtils.isNotEmpty(params)) {
      params.forEach((k, v) -> {
        if (StringKit.valueNotNull(v)) {
          eq(k, v);
        } else {
          if (null2IsNull) {
            isNull(k);
          }
        }
      });
    }
    return typedThis;
  }

  @Override
  public <V> Children allEq(boolean condition, BiPredicate<R, V> filter, Map<R, V> params, boolean null2IsNull) {
    if (condition && CollectionUtils.isNotEmpty(params)) {
      params.forEach((k, v) -> {
        if (filter.test(k, v)) {
          if (StringKit.valueNotNull(v)) {
            eq(k, v);
          } else {
            if (null2IsNull) {
              isNull(k);
            }
          }
        }
      });
    }
    return typedThis;
  }

  public Children of(Object queryObject) {

    if (queryObject != null) {

      Class<?> objectClass = queryObject.getClass();
      List<Field> fields = ReflectKit.getEntityFields(objectClass)
        .stream().filter(x -> !x.isAnnotationPresent(Ignore.class)).collect(Collectors.toList());
      for (Field field : fields) {
        String fieldName = field.getName();
        Object fieldValue = null;
        try {
          fieldValue = ReflectKit.getFieldValue(fieldName, queryObject);
        } catch (Exception e) {
          e.printStackTrace();
        }
        if (fieldValue != null && StringKit.isNotBlank(fieldValue.toString())) {
          final Object finalFieldValue = fieldValue;
          Mapping map = AnnotationUtils.getMergeAnnotation(field, Mapping.class);
          if (map != null && StringKit.hasText(map.value())) {
            fieldName = map.value();
          }
          SqlKeyword sqlKeyword = map != null ? map.compare() : EQ;
          if (columnMap.containsKey(fieldName) || (sqlKeyword.equals(LIKE) && fieldName.contains(StringPool.COMMA))) {
            String finalFieldName = fieldName;
            if (map != null && map.not()) {
              not(true);
            }
            if (sqlKeyword.equals(LIKE)) {
              SqlLike sqlLike = map.like();
              if (fieldName.contains(StringPool.COMMA)) {
                String[] split = fieldName.split(StringPool.COMMA);
                and(w -> {
                  boolean first = true;
                  for (String s : split) {
                    if (!first) {
                      w.or();
                    }
                    w.doCompare(true, () -> w.columnNameToFieldName(s), LIKE, () -> formatSql("{0}", SqlKit.concatLike(finalFieldValue, sqlLike)));
                    first = false;
                  }
                  return w;
                });
              } else {
                doCompare(true, () -> columnNameToFieldName(finalFieldName), LIKE, () -> formatSql("{0}", SqlKit.concatLike(finalFieldValue, sqlLike)));
              }
            } else if (sqlKeyword.equals(IS_NULL)) {
              if (Boolean.TRUE.equals(finalFieldValue)) {
                doCompare(true, () -> columnNameToFieldName(finalFieldName), IS_NULL);
              }
            } else if (sqlKeyword.equals(IS_NOT_NULL)) {
              if (Boolean.TRUE.equals(finalFieldValue)) {
                doCompare(true, () -> columnNameToFieldName(finalFieldName), IS_NOT_NULL);
              }
            } else if (sqlKeyword.equals(IN)) {
              if (finalFieldValue instanceof Collection) {
                doCompare(true, () -> columnNameToFieldName(finalFieldName), IN, inExpression((Collection<?>) finalFieldValue));
              }
            } else {
              doCompare(true, () -> columnNameToFieldName(finalFieldName), sqlKeyword, () -> formatSql("{0}", finalFieldValue));
            }
          }
        }
      }
    }

    return typedThis;
  }

  @Override
  public Children eq(boolean condition, R column, Object value) {
    return addCondition(condition, column, EQ, value);
  }

  @Override
  public Children notEq(boolean condition, R column, Object value) {
    return addCondition(condition, column, NE, value);
  }

  @Override
  public Children gt(boolean condition, R column, Object value) {
    return addCondition(condition, column, GT, value);
  }

  @Override
  public Children gtEq(boolean condition, R column, Object value) {
    return addCondition(condition, column, GE, value);
  }

  @Override
  public Children lt(boolean condition, R column, Object value) {
    return addCondition(condition, column, LT, value);
  }

  @Override
  public Children ltEq(boolean condition, R column, Object value) {
    return addCondition(condition, column, LE, value);
  }

  protected Children likeValue(boolean condition, R column, Object value, SqlLike sqlLike) {
    return doCompare(condition, () -> columnToFieldName(column), LIKE, () -> formatSql("{0}", SqlKit.concatLike(value, sqlLike)));
  }

  @Override
  public Children like(boolean condition, R column, Object value) {
    return likeValue(condition, column, value, SqlLike.DEFAULT);
  }

  @Override
  public Children notLike(boolean condition, R column, Object value) {
    return not(condition).like(condition, column, value);
  }

  @Override
  public Children likeLeft(boolean condition, R column, Object value) {
    return likeValue(condition, column, value, SqlLike.LEFT);
  }

  @Override
  public Children likeRight(boolean condition, R column, Object value) {
    return likeValue(condition, column, value, SqlLike.RIGHT);
  }

  @Override
  public Children between(boolean condition, R column, Object value1, Object value2) {
    return doCompare(condition, () -> columnToFieldName(column), BETWEEN,
      () -> formatSql("{0}", value1), AND, () -> formatSql("{0}", value2));
  }

  @Override
  public Children notBetween(boolean condition, R column, Object value1, Object value2) {
    return not(condition).between(condition, column, value1, value2);
  }

  @Override
  public Children and(boolean condition, Function<AbstractWhereWrapper<R>, AbstractWhereWrapper<R>> func) {
    return and(condition).addNestedCondition(condition, func);
  }

  @Override
  public Children or(boolean condition, Function<AbstractWhereWrapper<R>, AbstractWhereWrapper<R>> func) {
    return or(condition).addNestedCondition(condition, func);
  }

  @Override
  public Children nested(boolean condition, Function<AbstractWhereWrapper<R>, AbstractWhereWrapper<R>> func) {
    return addNestedCondition(condition, func);
  }

  protected Children not(boolean condition) {
    return doCompare(condition, NOT);
  }

  protected Children and(boolean condition) {
    return doCompare(condition, AND);
  }

  @Override
  public Children or(boolean condition) {
    return doCompare(condition, OR);
  }

  @Override
  public Children apply(boolean condition, String sql, Object... values) {
    return doCompare(condition, APPLY, () -> formatSql(sql, values));
  }

  @Override
  public Children exists(boolean condition, String sql) {
    return doCompare(condition, EXISTS, () -> String.format("(%s)", sql));
  }

  @Override
  public Children notExists(boolean condition, String sql) {
    return not(condition).exists(condition, sql);
  }

  @Override
  public Children isNull(boolean condition, R column) {
    return doCompare(condition, () -> columnToFieldName(column), IS_NULL);
  }

  @Override
  public Children isNotNull(boolean condition, R column) {
    return doCompare(condition, () -> columnToFieldNames(column), IS_NOT_NULL);
  }

  @Override
  public Children in(boolean condition, R column, Collection<?> values) {
    return doCompare(condition, () -> columnToFieldName(column), IN, inExpression(values));
  }

  @Override
  public Children inSql(boolean condition, R column, String inValue) {
    return doCompare(condition, () -> columnToFieldName(column), IN, () -> String.format("{0}", inValue));
  }

  @Override
  public Children notIn(boolean condition, R column, Collection<?> values) {
    return not(condition).in(condition, column, values);
  }

  @Override
  public Children notInSql(boolean condition, R column, String inValue) {
    return not(condition).inSql(condition, column, inValue);
  }

  SqlSegment inExpression(Collection<?> values) {
    return () -> values.stream().map(m -> formatSql("{0}", m))
      .collect(joining(StringPool.COMMA, StringPool.LEFT_BRACKET, StringPool.RIGHT_BRACKET));
  }

  protected Children doCompare(boolean condition, SqlSegment... sqlSegments) {
    if (condition) {
      normal.addAll(Arrays.asList(sqlSegments));
    }
    return typedThis;
  }

  protected String columnToString(R column) {
    if (column instanceof String) {
      return (String) column;
    } else if (column instanceof Getter) {
      return LambdaKit.columnName((Getter<R>) column);
    }
    throw new RuntimeException("仅支持 String, Getter 类型。");
  }

  public String columnToFieldName(R column) {
    return columnNameToFieldName(columnToString(column));
  }

  public String columnToSelectName(R column) {
    return columnNameToSelectName(columnToString(column));
  }

  protected String columnNameToFieldName(String columnName) {
    if (this.columnMap.containsKey(columnName)) {
      return this.columnMap.get(columnName).getFieldName();
    } else {
      return columnName;
    }
  }

  protected String columnNameToSelectName(String columnName) {
    if (this.columnMap.containsKey(columnName)) {
      return this.columnMap.get(columnName).getSelectName();
    } else {
      return columnName;
    }
  }

  protected String columnToFieldNames(R... columns) {
    return Arrays.stream(columns).map(this::columnToFieldName).collect(joining(StringPool.COMMA));
  }

  protected String columnToSelectNames(R... columns) {
    return Arrays.stream(columns).map(this::columnToFieldName).collect(joining(StringPool.COMMA));
  }

  protected String formatSql(String sql, Object... params) {
    return formatSqlIfNeed(true, sql, params);
  }

  protected String formatSqlIfNeed(boolean need, String sql, Object... params) {
    if (!need || StringKit.isEmpty(sql)) {
      return null;
    }
    if (CollectionUtils.isNotEmpty(params)) {
      for (int i = 0; i < params.length; ++i) {
        String paramName = Constants.WRAPPER_PARAM + paramSeq.incrementAndGet();
        sql = sql.replace(
          String.format("{%s}", i),
          String.format(Constants.WRAPPER_PARAM_FORMAT, Constants.WRAPPER, paramName));
        paramValues.put(paramName, params[i]);
      }
    }
    return sql;
  }

  protected Children addCondition(boolean condition, R column, SqlKeyword sqlKeyword, Object value) {
    return doCompare(condition, () -> columnToFieldName(column), sqlKeyword, () -> formatSql("{0}", value));
  }

  protected abstract AbstractWhereWrapper<R> nestedInstance();

  protected Children addNestedCondition(boolean condition, Function<AbstractWhereWrapper<R>, AbstractWhereWrapper<R>> func) {
    return doCompare(condition, LEFT_BRACKET, func.apply(nestedInstance()), RIGHT_BRACKET);
  }

}
