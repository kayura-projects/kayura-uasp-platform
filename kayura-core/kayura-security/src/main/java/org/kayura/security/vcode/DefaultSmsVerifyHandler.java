/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
