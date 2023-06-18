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
