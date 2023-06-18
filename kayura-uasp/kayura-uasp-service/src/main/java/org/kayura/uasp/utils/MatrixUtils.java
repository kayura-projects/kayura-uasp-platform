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

package org.kayura.uasp.utils;

import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;

import javax.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 二维码的生成类
 *
 * @author 夏亮(liangxia @ live.com)
 */
public abstract class MatrixUtils {

  private static final int BLACK = 0xFF000000;
  private static final int WHITE = 0xFFFFFFFF;

  public static BufferedImage toBufferedImage(BitMatrix matrix) {

    int width = matrix.getWidth();
    int height = matrix.getHeight();
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
      }
    }
    return image;
  }

  public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
    BufferedImage image = toBufferedImage(matrix);
    if (!ImageIO.write(image, format, file)) {
      throw new IOException("Could not write an image of format " + format + " to " + file);
    }
  }

  public static void writeToStream(BitMatrix matrix, String format, HttpServletResponse response) throws IOException {
    response.setCharacterEncoding("utf-8");
    response.setContentType("image/" + format);
    BufferedImage image = toBufferedImage(matrix);
    if (!ImageIO.write(image, format, response.getOutputStream())) {
      throw new IOException("Could not write an image of format " + format);
    }
  }
}
