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

package org.kayura.mybatis.plugin.paginator.dialect;

import org.kayura.mybatis.plugin.paginator.Dialect;
import org.kayura.mybatis.type.PageBounds;
import org.apache.ibatis.mapping.MappedStatement;

/**
 * @author 夏亮（liangxia@live.com）
 */
public class OracleDialect extends Dialect {

  public OracleDialect(MappedStatement mappedStatement, Object parameterObject, PageBounds pageBounds) {
    super(mappedStatement, parameterObject, pageBounds);
  }

  protected String getLimitString(String sql, String offsetName, int offset, String limitName, int limit) {
    sql = sql.trim();
    boolean isForUpdate = false;
    if (sql.toLowerCase().endsWith(" FOR UPDATE")) {
      sql = sql.substring(0, sql.length() - 11);
      isForUpdate = true;
    }

    StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
    if (offset > 0) {
      pagingSelect.append("SELECT * FROM ( select row_.*, rownum rownum_ from ( ");
    } else {
      pagingSelect.append("SELECT * FROM ( ");
    }
    pagingSelect.append(sql);
    if (offset > 0) {
      pagingSelect.append(" ) row_ ) WHERE rownum_ <= ? AND rownum_ > ?");
      setPageParameter("__offsetEnd", offset + limit, Integer.class);
      setPageParameter(offsetName, offset, Integer.class);
    } else {
      pagingSelect.append(" ) WHERE rownum <= ?");
      setPageParameter(limitName, limit, Integer.class);
    }

    if (isForUpdate) {
      pagingSelect.append(" FOR UPDATE");
    }

    return pagingSelect.toString();
  }

}
