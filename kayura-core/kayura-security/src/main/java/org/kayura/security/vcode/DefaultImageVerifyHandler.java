/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
