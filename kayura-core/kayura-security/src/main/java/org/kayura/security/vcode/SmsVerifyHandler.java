package org.kayura.security.vcode;

import org.kayura.type.Result;

public interface SmsVerifyHandler {

  VerifyCode build(String mobile, String templateId);

  Result verify(String mobile, String ucode, String vcode);
}
