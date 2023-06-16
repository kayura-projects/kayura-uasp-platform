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
