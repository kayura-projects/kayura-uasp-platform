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

package org.kayura.uasp.auth.handler;

import org.kayura.security.sneak.SneakHandler;
import org.kayura.security.sneak.SneakItem;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

@Component
public class CacheSneakHandler implements SneakHandler {

  private final Cache cache;

  public CacheSneakHandler(CacheManager cacheManager) {
    this.cache = cacheManager.getCache("KAYURA:SNEAK");
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
        throw new AuthenticationServiceException("该凭据已经超时间。");
      }
      cache.evict(key);
    } else {
      throw new AuthenticationServiceException("传入凭据验证失败。");
    }
    return item;
  }

}
