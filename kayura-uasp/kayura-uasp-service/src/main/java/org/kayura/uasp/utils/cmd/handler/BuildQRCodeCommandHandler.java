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
