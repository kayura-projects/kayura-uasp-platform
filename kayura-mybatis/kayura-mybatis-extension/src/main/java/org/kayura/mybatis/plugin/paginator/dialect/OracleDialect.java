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
