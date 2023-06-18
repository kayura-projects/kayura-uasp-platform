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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * FileSizeJsonSerializer
 *
 * @author 夏亮（liangxia@live.com）
 */
public class FileSizeJsonSerializer extends JsonSerializer<Long> {

  @Override
  public void serialize(Long size, JsonGenerator gen, SerializerProvider serializers)
    throws IOException {

    long kb = 1024;
    long mb = kb * 1024;
    long gb = mb * 1024;

    String result;

    if (size >= gb) {
      result = String.format("%.1f GB", (float) size / gb);
    } else if (size >= mb) {
      float f = (float) size / mb;
      result = String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
    } else if (size >= kb) {
      float f = (float) size / kb;
      result = String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
    } else {
      result = String.format("%d B", size);
    }

    gen.writeString(result);

  }

}
