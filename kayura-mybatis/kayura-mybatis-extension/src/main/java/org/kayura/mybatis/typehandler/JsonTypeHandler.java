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
