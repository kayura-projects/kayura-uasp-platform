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
