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

package org.kayura.security.vcode;

import org.kayura.except.ExceptUtils;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class DefaultImageCodeBuilder implements ImageCodeBuilder {

  private static final Random random = new Random();

  @Override
  public CodeResult build(int width, int height, int verifySize) {
    String vcode = VerifyCodeUtils.randomVerifyCode(verifySize);
    String image = build(width, height, vcode);
    return CodeResult.builder().setCode(vcode).setImage(image);
  }

  @Override
  public String build(int width, int height, String code) {

    ByteArrayOutputStream os = new ByteArrayOutputStream();
    try {
      outputImage(width, height, os, code);
    } catch (IOException e) {
      ExceptUtils.business("验证码生成失败。");
    }
    String imgCode = Base64.encodeBase64String(os.toByteArray());
    return "data:image/jpg;base64, " + imgCode;
  }

  @SuppressWarnings("IntegerDivisionInFloatingPointContext")
  private static void outputImage(int w, int h, OutputStream os, String code) throws IOException {

    int verifySize = code.length();
    BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    Random rand = new Random();
    Graphics2D g2 = image.createGraphics();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(Color.GRAY); // 设置边框色
    g2.fillRect(0, 0, w, h);

    Color c = getRandColor(200, 250);
    g2.setColor(c); // 设置背景色
    g2.fillRect(0, 2, w, h - 4);

    // 绘制干扰线
    Random random = new Random();
    g2.setColor(getRandColor(100, 160)); // 设置线条的颜色
    for (int i = 0; i < 80; i++) {
      int x = random.nextInt(w - 1);
      int y = random.nextInt(h - 1);
      int xl = random.nextInt(6) + 1;
      int yl = random.nextInt(12) + 1;
      g2.drawLine(x, y, x + xl + 40, y + yl + 20);
    }

    // 添加噪点
    float yawpRate = 0.05f; // 噪声率
    int area = (int) (yawpRate * w * h);
    for (int i = 0; i < area; i++) {
      int x = random.nextInt(w);
      int y = random.nextInt(h);
      int rgb = getRandomIntColor();
      image.setRGB(x, y, rgb);
    }

    shear(g2, w, h, c); // 使图片扭曲

    g2.setColor(getRandColor(100, 160));
    int fontSize = h - 4;
    Font font = new Font("Algerian", Font.ITALIC, fontSize);
    g2.setFont(font);
    char[] chars = code.toCharArray();
    for (int i = 0; i < verifySize; i++) {
      AffineTransform affine = new AffineTransform();
      affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1),
        (w / verifySize) * i + fontSize / 2, h / 2);
      g2.setTransform(affine);
      g2.drawChars(chars, i, 1, ((w - 10) / verifySize) * i + 5, h / 2 + fontSize / 2 - 10);
    }

    g2.dispose();
    ImageIO.write(image, "jpg", os);
  }

  private static Color getRandColor(int fc, int bc) {
    if (fc > 255)
      fc = 255;
    if (bc > 255)
      bc = 255;
    int r = fc + random.nextInt(bc - fc);
    int g = fc + random.nextInt(bc - fc);
    int b = fc + random.nextInt(bc - fc);
    return new Color(r, g, b);
  }

  private static int getRandomIntColor() {
    int[] rgb = getRandomRgb();
    int color = 0;
    for (int c : rgb) {
      color = color << 8;
      color = color | c;
    }
    return color;
  }

  private static int[] getRandomRgb() {
    int[] rgb = new int[3];
    for (int i = 0; i < 3; i++) {
      rgb[i] = random.nextInt(255);
    }
    return rgb;
  }

  private static void shear(Graphics g, int w1, int h1, Color color) {
    shearX(g, w1, h1, color);
    shearY(g, w1, h1, color);
  }

  private static void shearX(Graphics g, int w1, int h1, Color color) {

    int period = random.nextInt(2);
    int frames = 1;
    int phase = random.nextInt(2);

    for (int i = 0; i < h1; i++) {
      double d = (double) (0) * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
      g.copyArea(0, i, w1, 1, (int) d, 0);
      g.setColor(color);
      g.drawLine((int) d, i, 0, i);
      g.drawLine((int) d + w1, i, w1, i);
    }

  }

  private static void shearY(Graphics g, int w1, int h1, Color color) {

    int period = random.nextInt(40) + 10; // 50;
    int frames = 20;
    int phase = 7;

    for (int i = 0; i < w1; i++) {
      double d = (double) (period >> 1)
        * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
      g.copyArea(i, 0, 1, h1, 0, (int) d);
      g.setColor(color);
      g.drawLine(i, (int) d, i, 0);
      g.drawLine(i, (int) d + h1, i, h1);
    }

  }
}
