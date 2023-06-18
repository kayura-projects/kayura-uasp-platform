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
