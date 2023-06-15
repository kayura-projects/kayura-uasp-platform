package org.kayura.security.vcode;

public class VerifyCode {

  private String ucode;
  private String vcode;

  public static VerifyCode builder() {
    return new VerifyCode();
  }

  public String getUcode() {
    return ucode;
  }

  public VerifyCode setUcode(String ucode) {
    this.ucode = ucode;
    return this;
  }

  public String getVcode() {
    return vcode;
  }

  public VerifyCode setVcode(String vcode) {
    this.vcode = vcode;
    return this;
  }
}
