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
