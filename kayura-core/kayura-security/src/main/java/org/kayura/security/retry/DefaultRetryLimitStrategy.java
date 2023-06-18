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
