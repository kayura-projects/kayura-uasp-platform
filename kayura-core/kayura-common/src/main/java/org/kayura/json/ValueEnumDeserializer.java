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

import org.kayura.utils.EnumUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.BeanUtils;

import java.io.IOException;

@SuppressWarnings("ALL")
public class ValueEnumDeserializer extends JsonDeserializer<Enum<?>> {

  @Override
  public Enum<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

    String value = jsonParser.getText();
    String currentName = jsonParser.currentName();
    Object currentValue = jsonParser.getCurrentValue();
    Class propertyType = BeanUtils.findPropertyType(currentName, currentValue.getClass());
    if (propertyType.isEnum()) {
      return (Enum<?>) EnumUtils.valueOf(propertyType, value);
    }
    return null;
  }

}
