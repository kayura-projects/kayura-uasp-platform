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
