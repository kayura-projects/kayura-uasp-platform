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

package org.kayura.uasp.auth.handler;

import org.kayura.security.sneak.SneakHandler;
import org.kayura.security.sneak.SneakItem;
import org.kayura.uasp.utils.CacheConsts;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class CacheSneakHandler implements SneakHandler, CacheConsts {

  private final Cache cache;

  public CacheSneakHandler(CacheManager cacheManager) {
    this.cache = cacheManager.getCache(SNEAK_KEY);
  }

  @Override
  public boolean build(String cacheKey, SneakItem item) {
    cache.put(cacheKey, item);
    return false;
  }

  @Override
  public SneakItem find(String key) {

    SneakItem item;
    Cache.ValueWrapper wrapper = cache.get(key);
    if (wrapper != null) {
      item = cache.get(key, SneakItem.class);
      if (item != null && System.currentTimeMillis() > item.getExpire()) {
        throw new RuntimeException("该凭据已经超时间。");
      }
      cache.evict(key);
    } else {
      throw new RuntimeException("传入凭据验证失败。");
    }
    return item;
  }

}
