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
