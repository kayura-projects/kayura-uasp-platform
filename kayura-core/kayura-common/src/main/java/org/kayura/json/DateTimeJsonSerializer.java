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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateTimeJsonSerializer
 *
 * @author 夏亮（liangxia@live.com）
 */
public class DateTimeJsonSerializer extends JsonSerializer<Date> implements Constant {

  @Override
  public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers)
    throws IOException {

    SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME);
    String formattedDate = formatter.format(value);
    gen.writeString(formattedDate);
  }

}
