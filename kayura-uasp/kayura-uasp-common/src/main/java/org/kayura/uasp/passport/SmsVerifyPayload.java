package org.kayura.uasp.passport;

import javax.validation.constraints.NotBlank;

public class SmsVerifyPayload {

  @NotBlank
  private String mobile;
  @NotBlank
  private String vcode;

  public String getVcode() {
    return vcode;
  }

  public SmsVerifyPayload setVcode(String vcode) {
    this.vcode = vcode;
    return this;
  }

  public String getMobile() {
    return mobile;
  }

  public SmsVerifyPayload setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }
}
