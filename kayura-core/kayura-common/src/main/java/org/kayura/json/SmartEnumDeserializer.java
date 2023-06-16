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
