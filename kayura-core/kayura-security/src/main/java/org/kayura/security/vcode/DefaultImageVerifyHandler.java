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

package org.kayura.security.vcode;

import org.kayura.type.Result;
import org.kayura.utils.Assert;
import org.kayura.utils.DateUtils;
import org.kayura.utils.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

public class DefaultImageVerifyHandler implements ImageVerifyHandler {

  private final ImageCodeBuilder imageCodeBuilder;
  private final Cache cache;
  private final int second;

  public DefaultImageVerifyHandler(
    ImageCodeBuilder imageCodeBuilder,
    CacheManager cacheManager,
    int second
  ) {
    this.imageCodeBuilder = imageCodeBuilder;
    this.cache = cacheManager.getCache("IMAGE_CODE");
    this.second = second;
  }

  private String makeCacheKey(String sessionId, String vcode) {
    return sessionId + "#" + vcode.toUpperCase();
  }

  private String needCacheKey(String sessionId) {
    return "NEED_VERIFY_CODE#" + sessionId;
  }

  @Override
  public void setNeed(String sessionId) {

    Assert.hasText(sessionId, "缺少会话ID。");

    String cacheKey = needCacheKey(sessionId);
    String cacheValue = String.valueOf(DateUtils.longPlusSeconds(second));
    cache.put(cacheKey, cacheValue);
  }

  @Override
  public boolean isNeed(String sessionId) {

    Assert.hasText(sessionId, "缺少会话ID。");

    String cacheKey = needCacheKey(sessionId);
    Cache.ValueWrapper wrapper = cache.get(cacheKey);
    return wrapper != null;
  }

  @Override
  public void clean(String sessionId) {

    Assert.hasText(sessionId, "缺少会话ID。");

    String cacheKey = needCacheKey(sessionId);
    cache.evict(cacheKey);
  }

  @Override
  public String build(String sessionId) {

    Assert.hasText(sessionId, "缺少会话ID。");

    String vcode = VerifyCodeUtils.randomVerifyCode(6);

    String cacheKey = makeCacheKey(sessionId, vcode);
    String cacheValue = String.valueOf(DateUtils.longPlusSeconds(second));
    cache.put(cacheKey, cacheValue);

    String imgCode = imageCodeBuilder.build(200, 80, vcode);
    return imgCode;
  }

  @Override
  public Result verify(String sessionId, String vcode) {

    if (StringUtils.isBlank(vcode)) {
      return Result.fail("缺少手机验证码。");
    }
    if (StringUtils.isBlank(sessionId)) {
      return Result.fail("缺少会话ID。");
    }

    String key = makeCacheKey(sessionId, vcode);
    Cache.ValueWrapper wrapper = cache.get(key);
    if (wrapper != null) {
      String cacheValue = cache.get(key, String.class);
      if (StringUtils.hasText(cacheValue) && DateUtils.longPlusSeconds(0) < Long.parseLong(cacheValue)) {
        cache.evict(key);
        return Result.ok();
      }
      return Result.fail("该验证码经过期。");
    }
    return Result.fail("验证码信息错误。");
  }

}
