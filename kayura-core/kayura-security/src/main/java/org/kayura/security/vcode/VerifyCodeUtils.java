package org.kayura.security.vcode;

import java.util.Random;

/**
 * The type Verify code utils.
 */
public abstract class VerifyCodeUtils {

  private static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

  /**
   * Generate verify code string.
   *
   * @param verifySize to verify size
   * @param sources    the sources
   * @return the string
   */
  public static String randomVerifyCode(int verifySize, String sources) {
    if (sources == null || sources.length() == 0) {
      sources = VERIFY_CODES;
    }
    int codesLen = sources.length();
    Random rand = new Random(System.currentTimeMillis());
    StringBuilder verifyCode = new StringBuilder(verifySize);
    for (int i = 0; i < verifySize; i++) {
      verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
    }
    return verifyCode.toString();
  }

  /**
   * Random verify code string.
   *
   * @param verifySize the verify size
   * @return the string
   */
  public static String randomVerifyCode(int verifySize) {
    return randomVerifyCode(verifySize, VERIFY_CODES);
  }

}
