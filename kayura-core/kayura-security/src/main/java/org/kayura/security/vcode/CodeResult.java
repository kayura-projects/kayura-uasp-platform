package org.kayura.security.vcode;

public class CodeResult {

  private String code;
  private String image;

  public static CodeResult builder() {
    return new CodeResult();
  }

  public String getCode() {
    return code;
  }

  public CodeResult setCode(String code) {
    this.code = code;
    return this;
  }

  public String getImage() {
    return image;
  }

  public CodeResult setImage(String image) {
    this.image = image;
    return this;
  }
}
