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

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author liang.xia
 */
public class SqlHelper {

  private static final Log logger = LogFactory.getLog(SqlHelper.class);

  public static int getCount(final MappedStatement mappedStatement, final Object parameterObject,
                             final BoundSql boundSql, Dialect dialect) throws SQLException {
    final String count_sql = dialect.getCountSQL();

    if (logger.isDebugEnabled()) {
      logger.debug("Total count SQL [" + count_sql + "] ");
      logger.debug("Total count Parameters: " + parameterObject);
    }

    Connection connection = null;
    PreparedStatement countStmt = null;
    ResultSet rs = null;
    try {
      connection = mappedStatement.getConfiguration().getEnvironment().getDataSource()
        .getConnection();
      countStmt = connection.prepareStatement(count_sql);
      DefaultParameterHandler handler = new DefaultParameterHandler(mappedStatement,
        parameterObject, boundSql);
      handler.setParameters(countStmt);

      rs = countStmt.executeQuery();
      int count = 0;
      if (rs.next()) {
        count = rs.getInt(1);
      }
      if (logger.isDebugEnabled()) {
        logger.debug("Total count: " + count);
      }
      return count;
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
      } finally {
        try {
          if (countStmt != null) {
            countStmt.close();
          }
        } finally {
          if (connection != null && !connection.isClosed()) {
            connection.close();
          }
        }
      }
    }
  }
}
