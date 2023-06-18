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

package org.kayura.security.vcode;

import org.kayura.security.sms.ProxySmsManager;
import org.kayura.type.Result;
import org.kayura.utils.DateUtils;
import org.kayura.utils.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DefaultSmsVerifyHandler implements SmsVerifyHandler {

  private final ProxySmsManager proxySmsManager;
  private final Cache cache;
  private final int second;
  private final boolean isMock;

  public DefaultSmsVerifyHandler(ProxySmsManager proxySmsManager,
                                 CacheManager cacheManager,
                                 int second,
                                 boolean isMock) {
    this.proxySmsManager = proxySmsManager;
    this.cache = cacheManager.getCache("SMS_CODE");
    this.second = second;
    this.isMock = isMock;
  }

  private String makeKey(String mobile, String ucode, String vcode) {
    return mobile + "#" + ucode + "#" + vcode.toUpperCase();
  }

  @Override
  public VerifyCode build(String mobile, String templateId) {

    String ucode = RandomStringUtils.randomAlphabetic(8);
    String vcode = RandomStringUtils.randomNumeric(6);

    String cacheKey = makeKey(mobile, ucode, vcode);
    long cacheValue = DateUtils.longPlusSeconds(second);
    cache.put(cacheKey, cacheValue);

    VerifyCode code = VerifyCode.builder().setUcode(ucode);
    if (!isMock) {
      Map<String, Object> params = new HashMap<>();
      params.put("VCODE", vcode);
      params.put("TIME", DateUtils.friendly(Duration.ofSeconds(second)));
      proxySmsManager.sendSms(templateId, Collections.singletonList(mobile), params);
    } else {
      code.setVcode(vcode);
    }

    return code;
  }

  @Override
  public Result verify(String mobile, String ucode, String vcode) {

    if (StringUtils.isBlank(vcode)) {
      return Result.fail("缺少手机验证码。");
    }
    if (StringUtils.isBlank(ucode)) {
      return Result.fail("缺少用户识别码。");
    }

    String cacheKey = makeKey(mobile, ucode, vcode);
    Cache.ValueWrapper wrapper = cache.get(cacheKey);
    if (wrapper != null) {
      String cacheValue = cache.get(cacheKey, String.class);
      if (StringUtils.hasText(cacheValue) && DateUtils.longPlusSeconds(0) < Long.parseLong(cacheValue)) {
        cache.evict(cacheKey);
        return Result.ok();
      }
      return Result.fail("该验证码经过期。");
    }
    return Result.fail("验证码信息错误。");
  }

}
