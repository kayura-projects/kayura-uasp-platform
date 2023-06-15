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
package org.kayura.mybatis.plugin.paginator.dialect;

import org.kayura.mybatis.plugin.paginator.Dialect;
import org.kayura.mybatis.type.PageBounds;
import org.apache.ibatis.mapping.MappedStatement;

/**
 * @author 夏亮（liangxia@live.com）
 */
public class SQLServer2005Dialect extends Dialect {

  public SQLServer2005Dialect(MappedStatement mappedStatement, Object parameterObject, PageBounds pageBounds) {
    super(mappedStatement, parameterObject, pageBounds);
  }

  protected String getLimitString(String sql, String offsetName, int offset, String limitName, int limit) {
    StringBuffer pagingBuilder = new StringBuffer();
    String orderby = getOrderByPart(sql);
    String distinctStr = "";

    String loweredString = sql.toLowerCase();
    String sqlPartString = sql;
    if (loweredString.trim().startsWith("select")) {
      int index = 6;
      if (loweredString.startsWith("select distinct")) {
        distinctStr = "DISTINCT ";
        index = 15;
      }
      sqlPartString = sqlPartString.substring(index);
    }
    pagingBuilder.append(sqlPartString);

    if (orderby == null || orderby.length() == 0) {
      orderby = "ORDER BY CURRENT_TIMESTAMP";
    }

    StringBuffer result = new StringBuffer();
    result.append("WITH query AS (SELECT ").append(distinctStr).append("TOP 100 PERCENT ")
      .append(" ROW_NUMBER() OVER (").append(orderby).append(") as __row_number__, ").append(pagingBuilder)
      .append(") SELECT * FROM query WHERE __row_number__ > ? AND __row_number__ <= ?")
      .append(" ORDER BY __row_number__");
    setPageParameter(offsetName, offset, Integer.class);
    setPageParameter("__offsetEnd", offset + limit, Integer.class);
    return result.toString();
  }

  static String getOrderByPart(String sql) {
    String loweredString = sql.toLowerCase();
    int orderByIndex = loweredString.indexOf("order by");
    if (orderByIndex != -1) {
      return sql.substring(orderByIndex);
    } else {
      return "";
    }
  }
}
