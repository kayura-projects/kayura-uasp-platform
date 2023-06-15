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
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings({"rawtypes", "unchecked"})
public class SmartEnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

  private final BaseTypeHandler typeHandler;

  public SmartEnumTypeHandler(Class<E> type) {
    if (type == null) {
      throw new IllegalArgumentException("Type argument cannot be null");
    }
    if (EnumValue.class.isAssignableFrom(type)) {
      typeHandler = new ValueEnumTypeHandler(type);
    } else {
      typeHandler = new EnumTypeHandler<>(type);
    }
  }

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
    typeHandler.setNonNullParameter(ps, i, parameter, jdbcType);
  }

  @Override
  public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return (E) typeHandler.getNullableResult(rs, columnName);
  }

  @Override
  public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return (E) typeHandler.getNullableResult(rs, columnIndex);
  }

  @Override
  public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return (E) typeHandler.getNullableResult(cs, columnIndex);
  }
}
