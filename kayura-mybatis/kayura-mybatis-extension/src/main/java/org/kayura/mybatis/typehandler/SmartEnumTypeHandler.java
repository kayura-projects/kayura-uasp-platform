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
