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
