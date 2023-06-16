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
