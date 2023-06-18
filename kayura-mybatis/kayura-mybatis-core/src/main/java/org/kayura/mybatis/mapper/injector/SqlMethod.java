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
