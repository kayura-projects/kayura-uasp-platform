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
