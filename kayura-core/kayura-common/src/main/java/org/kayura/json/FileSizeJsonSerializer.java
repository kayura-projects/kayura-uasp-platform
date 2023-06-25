/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
