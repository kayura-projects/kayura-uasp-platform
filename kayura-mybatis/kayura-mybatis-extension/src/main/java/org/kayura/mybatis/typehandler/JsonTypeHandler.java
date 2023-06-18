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
