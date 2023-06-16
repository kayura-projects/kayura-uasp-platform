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
