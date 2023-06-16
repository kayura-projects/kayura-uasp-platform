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
package org.kayura.mybatis.plugin.paginator.dialect;

import org.kayura.mybatis.plugin.paginator.Dialect;
import org.kayura.mybatis.type.PageBounds;
import org.apache.ibatis.mapping.MappedStatement;

/**
 * @author 夏亮（liangxia@live.com）
 */
public class MySQLDialect extends Dialect {

  public MySQLDialect(MappedStatement mappedStatement, Object parameterObject, PageBounds pageBounds) {
    super(mappedStatement, parameterObject, pageBounds);
  }

  protected String getLimitString(String sql, String offsetName, int offset, String limitName, int limit) {
    StringBuffer buffer = new StringBuffer(sql.length() + 20).append(sql);
    if (offset > 0) {
      buffer.append(" LIMIT ?, ?");
      setPageParameter(offsetName, offset, Integer.class);
      setPageParameter(limitName, limit, Integer.class);
    } else {
      buffer.append(" LIMIT ?");
      setPageParameter(limitName, limit, Integer.class);
    }
    return buffer.toString();
  }

}
