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
package org.kayura.uasp.utils.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.uasp.utils.cmd.BuildQRCodeCommand;
import org.kayura.uasp.utils.MatrixUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Hashtable;

@Component
public class BuildQRCodeCommandHandler implements CommandHandler<BuildQRCodeCommand, Void> {

  @Override
  public Void execute(BuildQRCodeCommand command) {

    String content = command.getContent();
    int width = command.getWidth();
    int height = command.getHeight();
    String format = command.getFormat();
    HttpServletResponse response = command.getResponse();

    Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
    hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
    hints.put(EncodeHintType.MARGIN, 0);
    try {
      MultiFormatWriter matrix = new MultiFormatWriter();
      BitMatrix bitMatrix = matrix.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
      MatrixUtils.writeToStream(bitMatrix, format, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
