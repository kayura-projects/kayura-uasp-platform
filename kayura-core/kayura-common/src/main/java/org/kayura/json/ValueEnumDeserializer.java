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
