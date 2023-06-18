/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
