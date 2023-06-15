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

package org.kayura.json;

import org.kayura.utils.Constant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 将 {@link Integer} 类型转换成JSON格式时,使用字符类型。
 *
 * @author 夏亮（liangxia@live.com）
 */
public class BooleanToStringJsonSerializer extends JsonSerializer<Boolean> implements Constant {

  @Override
  public void serialize(Boolean value, JsonGenerator gen, SerializerProvider serializers)
    throws IOException {

    gen.writeString(value != null ? (value ? TRUE : FALSE) : "");
  }

}
