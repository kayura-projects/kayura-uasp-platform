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

package org.kayura.mybatis.mapper.injector;

import org.kayura.mybatis.toolkit.StringKit;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

public class SqlMethod {

  protected static final Log logger = LogFactory.getLog(SqlMethod.class);

  private final String method;
  private final String sql;

  public SqlMethod(String method, String sql) {
    this.method = method;
    this.sql = sql;
  }

  public String formatSql(Object... args) {
    String sqlScript = StringKit.format(this.sql, args);
    if (logger.isTraceEnabled()) {
      logger.trace(method + " -> SQL: " + sqlScript);
    }
    return sqlScript;
  }

  public String method() {
    return method;
  }

}
