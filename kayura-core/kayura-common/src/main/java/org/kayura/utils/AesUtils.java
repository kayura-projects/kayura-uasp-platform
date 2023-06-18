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
