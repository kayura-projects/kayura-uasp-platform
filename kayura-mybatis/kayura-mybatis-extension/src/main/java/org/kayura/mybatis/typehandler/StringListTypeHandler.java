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
