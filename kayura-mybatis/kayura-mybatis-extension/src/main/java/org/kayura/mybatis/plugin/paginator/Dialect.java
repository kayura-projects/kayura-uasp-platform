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

/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.mybatis.plugin.paginator;

import org.kayura.mybatis.toolkit.StringKit;
import org.kayura.mybatis.type.PageBounds;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liang.xia
 */
public abstract class Dialect {

  protected MappedStatement mappedStatement;
  protected PageBounds pageBounds;
  protected Object parameterObject;
  protected BoundSql boundSql;
  protected List<ParameterMapping> parameterMappings;
  protected Map<String, Object> pageParameters = new HashMap<>();

  private String pageSQL;
  private String countSQL;

  public Dialect(MappedStatement mappedStatement, Object parameterObject, PageBounds pageBounds) {
    this.mappedStatement = mappedStatement;
    this.parameterObject = parameterObject;
    this.pageBounds = pageBounds;

    init();
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  protected void init() {

    boundSql = mappedStatement.getBoundSql(parameterObject);
    parameterMappings = new ArrayList<>(boundSql.getParameterMappings());

    if (parameterObject instanceof Map) {
      pageParameters.putAll((Map) parameterObject);
    } else {
      for (ParameterMapping parameterMapping : parameterMappings) {
        pageParameters.put(parameterMapping.getProperty(), parameterObject);
      }
    }

    StringBuffer bufferSql = new StringBuffer(boundSql.getSql().trim());
    if (bufferSql.lastIndexOf(";") == bufferSql.length() - 1) {
      bufferSql.deleteCharAt(bufferSql.length() - 1);
    }

    String sql = bufferSql.toString();
    pageSQL = sql;
    if (StringKit.isNotBlank(pageBounds.getOrderBy())) {
      pageSQL = getSortString(sql, pageBounds.getOrderBy());
    }
    if (pageBounds.getOffset() != RowBounds.NO_ROW_OFFSET || pageBounds.getLimit() != RowBounds.NO_ROW_LIMIT) {
      pageSQL = getLimitString(pageSQL, "__offset", pageBounds.getOffset(), "__limit", pageBounds.getLimit());
    }

    countSQL = getCountString(sql);
  }

  public List<ParameterMapping> getParameterMappings() {
    return parameterMappings;
  }

  public Object getParameterObject() {
    return pageParameters;
  }

  public String getPageSQL() {
    return pageSQL;
  }

  protected void setPageParameter(String name, Object value, Class<?> type) {
    ParameterMapping parameterMapping = new ParameterMapping.Builder(mappedStatement.getConfiguration(), name, type)
      .build();
    parameterMappings.add(parameterMapping);
    pageParameters.put(name, value);
  }

  public String getCountSQL() {
    return countSQL;
  }

  protected String getLimitString(String sql, String offsetName, int offset, String limitName, int limit) {
    throw new UnsupportedOperationException("paged queries not supported");
  }

  protected String getCountString(String sql) {
    return "SELECT COUNT(1) FROM (" + sql + ") tmp_count";
  }

  protected String getSortString(String sql, String orderByString) {

    if (StringKit.isBlank(orderByString)) {
      return sql;
    }

    StringBuffer buffer = new StringBuffer(sql);
    buffer.append(" ORDER BY ").append(orderByString);
    return buffer.toString();
  }

}
