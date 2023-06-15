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

import org.kayura.type.EnumValue;
import org.kayura.utils.EnumUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 字符型集合类型处理器。
 *
 * @author 夏亮（liangxia@live.com）
 */
public class ValueEnumTypeHandler<E extends EnumValue> extends BaseTypeHandler<EnumValue> {

  private final Class<E> type;

  public ValueEnumTypeHandler(Class<E> type) {
    if (type == null) {
      throw new IllegalArgumentException("Type argument cannot be null");
    }
    this.type = type;
  }

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, EnumValue parameter, JdbcType jdbcType) throws SQLException {
    ps.setString(i, parameter.toString());
  }

  @Override
  public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return valueOf(rs.getString(columnName));
  }

  @Override
  public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return valueOf(rs.getString(columnIndex));
  }

  @Override
  public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return valueOf(cs.getString(columnIndex));
  }

  private E valueOf(String value) {
    return EnumUtils.valueOf(type, value);
  }

}
