package org.kayura.security.vcode;

/**
 * The interface Image code builder.
 */
public interface ImageCodeBuilder {

  /**
   * Make Base64 Image string.
   *
   * @param width  the width
   * @param height the height
   * @param size   the size
   * @return the string
   */
  CodeResult build(int width, int height, int size);

  /**
   * Make Base64 Image string.
   *
   * @param width  the width
   * @param height the height
   * @param code   the code
   * @return the string
   */
  String build(int width, int height, String code);

}
