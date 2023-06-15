/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
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
