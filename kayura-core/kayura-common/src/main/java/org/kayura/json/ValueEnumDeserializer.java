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
