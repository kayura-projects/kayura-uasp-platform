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
