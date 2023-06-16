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

package org.kayura.utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public abstract class AesUtils {

  /**
   * 对字节进行 AES 加密.
   *
   * @param rawBytes   原始字节内容.
   * @param encryptKey 私有密钥.
   * @return 返回加密后的字节.
   */
  public static byte[] aesEncrypt(byte[] rawBytes, String encryptKey)
    throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
    NoSuchPaddingException, InvalidKeyException {

    KeyGenerator ken = KeyGenerator.getInstance("AES");
    ken.init(128, new SecureRandom(encryptKey.getBytes(StandardCharsets.UTF_8)));

    Cipher cipher = Cipher.getInstance("AES");
    Key key = new SecretKeySpec(ken.generateKey().getEncoded(), "AES");
    cipher.init(Cipher.ENCRYPT_MODE, key);

    byte[] encValue = cipher.doFinal(rawBytes);
    return encValue;
  }

  /**
   * 对节进行 AES 解密.
   *
   * @param encBytes   加密后的字节内容.
   * @param decryptKey 私有密钥.
   * @return 返回解密后的字节.
   */
  public static byte[] aesDecrypt(byte[] encBytes, String decryptKey)
    throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
    IllegalBlockSizeException, BadPaddingException {

    KeyGenerator ken = KeyGenerator.getInstance("AES");
    ken.init(128, new SecureRandom(decryptKey.getBytes(StandardCharsets.UTF_8)));

    Cipher cipher = Cipher.getInstance("AES");
    Key key = new SecretKeySpec(ken.generateKey().getEncoded(), "AES");
    cipher.init(Cipher.DECRYPT_MODE, key);

    byte[] rawValue = cipher.doFinal(encBytes);
    return rawValue;
  }

}
