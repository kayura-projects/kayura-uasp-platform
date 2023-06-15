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
