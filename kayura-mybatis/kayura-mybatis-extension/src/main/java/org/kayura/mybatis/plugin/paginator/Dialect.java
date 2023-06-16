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
