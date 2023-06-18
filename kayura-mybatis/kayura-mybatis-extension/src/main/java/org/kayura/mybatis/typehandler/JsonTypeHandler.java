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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.kayura.mybatis.toolkit.StringKit;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JSON 字段转换处理器
 *
 * @author 夏亮（liangxia@live.com）
 */
public class JsonTypeHandler<E extends Serializable> extends BaseTypeHandler<E> {

  private final Class<E> type;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public JsonTypeHandler(Class<E> type) {
    if (type == null)
      throw new IllegalArgumentException("Type argument cannot be null");
    this.type = type;
  }

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
    String s;
    try {
      s = objectMapper.writeValueAsString(parameter);
      ps.setString(i, s);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  private E getJsonObject(String json) {
    E o = null;
    try {
      if (StringKit.isNotEmpty(json)) {
        o = objectMapper.readValue(json, type);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return o;
  }

  @Override
  public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return getJsonObject(rs.getString(columnName));
  }

  @Override
  public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return getJsonObject(rs.getString(columnIndex));
  }

  @Override
  public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return getJsonObject(cs.getString(columnIndex));
  }

}
