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
