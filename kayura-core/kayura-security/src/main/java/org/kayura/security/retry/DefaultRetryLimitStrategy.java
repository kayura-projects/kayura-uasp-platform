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

package org.kayura.security.retry;

import org.kayura.type.Result;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.DateUtils;
import org.kayura.utils.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.time.Duration;
import java.util.List;

public class DefaultRetryLimitStrategy implements RetryLimitStrategy {

  private final Cache cache;
  private final List<Integer> policy;
  private final MessageResolver messageResolver;

  public DefaultRetryLimitStrategy(CacheManager cacheManager,
                                   MessageResolver messageResolver,
                                   List<Integer> policy) {
    this.cache = cacheManager.getCache("RETRY_LIMIT");
    this.policy = policy;
    this.messageResolver = messageResolver;
  }

  private StoreRetry readStoreRetry(String userName) {

    StoreRetry storeRetry = null;
    Cache.ValueWrapper wrapper = this.cache.get(userName);
    if (wrapper != null) {
      String cacheValue = this.cache.get(userName, String.class);
      if (StringUtils.hasText(cacheValue)) {
        storeRetry = StoreRetry.of(cacheValue);
      }
    }

    return storeRetry;
  }

  @Override
  public Result retryCheck(String userName) {

    if (CollectionUtils.isNotEmpty(this.policy)) {
      StoreRetry store = this.readStoreRetry(userName);
      if (store != null && store.getNextTime() > 0) {
        long wait = store.getNextTime() - DateUtils.longPlusSeconds(0);
        if (wait > 0) {
          Duration duration = Duration.ofSeconds(wait);
          return Result.fail("尝试次数过多，可在 " + DateUtils.friendly(duration) + " 后重试。");
        }
      }
    }
    return Result.ok();
  }

  @Override
  public RetryResult write(String userName) {

    RetryResult result = RetryResult.builder();

    if (CollectionUtils.isNotEmpty(this.policy)) {

      int used = 0;
      StoreRetry store = this.readStoreRetry(userName);
      if (store != null) {
        this.cache.evict(userName);
        used = store.getUsed();
      }

      if (used >= this.policy.size()) {
        result.setLocked(true);
      } else {
        int wait = this.policy.get(used);
        result.setWait(wait);
        result.setUsed(used + 1);

        long waitTime = DateUtils.longPlusSeconds(wait);
        this.cache.put(userName, result.getUsed() + "," + waitTime);
      }

      result.setMax(this.policy.size());
      result.setMessage(this.messageResolver.resolve(result));
    }

    return result;
  }

  @Override
  public void clean(String userName) {
    this.cache.evict(userName);
  }

}
