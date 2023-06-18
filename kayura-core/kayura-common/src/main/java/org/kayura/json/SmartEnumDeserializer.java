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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import org.kayura.type.EnumValue;
import org.kayura.utils.EnumUtils;
import org.kayura.utils.StringUtils;

import java.io.IOException;

@SuppressWarnings("ALL")
public class SmartEnumDeserializer extends JsonDeserializer<Enum> implements ContextualDeserializer {

  private Class classType;

  @Override
  public Enum deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
    if (StringUtils.isEmpty(jsonParser.getText())) {
      return null;
    }
    if (EnumValue.class.isAssignableFrom(classType)) {
      return (Enum) EnumUtils.valueOf(classType, jsonParser.getValueAsString());
    } else {
      return null;
    }
  }

  @Override
  public JsonDeserializer<?> createContextual(DeserializationContext ctx, BeanProperty property) throws JsonMappingException {
    Class rawCls = ctx.getContextualType().getRawClass();
    SmartEnumDeserializer clone = new SmartEnumDeserializer();
    clone.setClassType(rawCls);
    return clone;
  }

  public void setClassType(Class classType) {
    this.classType = classType;
  }

}
