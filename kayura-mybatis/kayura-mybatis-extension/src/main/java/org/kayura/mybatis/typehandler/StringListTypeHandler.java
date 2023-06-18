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

package org.kayura.mybatis.typehandler;

import org.kayura.type.StringList;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 字符型集合类型处理器。
 *
 * @author 夏亮（liangxia@live.com）
 */
@MappedTypes(StringList.class)
@MappedJdbcTypes({JdbcType.VARCHAR, JdbcType.NVARCHAR, JdbcType.LONGVARCHAR, JdbcType.LONGNVARCHAR})
public class StringListTypeHandler extends BaseTypeHandler<StringList> {

  public static final String LIST_SPLIT = ",";

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, StringList parameter, JdbcType jdbcType)
    throws SQLException {

    if (parameter != null && !parameter.isEmpty()) {
      String join = StringUtils.join(parameter.toArray(), LIST_SPLIT);
      ps.setString(i, join);
    } else {
      ps.setString(i, null);
    }
  }

  @Override
  public StringList getNullableResult(ResultSet rs, String columnName) throws SQLException {

    String list = rs.getString(columnName);
    return stringToList(list);
  }

  private StringList stringToList(String list) {

    StringList strList = new StringList();

    if (StringUtils.isNotBlank(list)) {
      String[] split = list.split(LIST_SPLIT);
      for (String s : split) {
        if (StringUtils.isNotEmpty(s)) {
          strList.add(s);
        }
      }
    }

    return strList;
  }

  @Override
  public StringList getNullableResult(ResultSet rs, int columnIndex) throws SQLException {

    String list = rs.getString(columnIndex);
    return stringToList(list);
  }

  @Override
  public StringList getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {

    String list = cs.getString(columnIndex);
    return stringToList(list);
  }

}
