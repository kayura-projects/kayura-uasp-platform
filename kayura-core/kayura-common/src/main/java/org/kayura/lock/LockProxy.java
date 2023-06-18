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

package org.kayura.lock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;

public class LockProxy {

  private static final Log LOG = LogFactory.getLog(LockProxy.class);
  private final Map<String, LockProvider> lockProviders = new ConcurrentHashMap<>();
  private final Map<String, Lock> lockCache = new ConcurrentHashMap<>();
  private final LockProvider defaultProvider;

  public LockProxy(Map<String, LockProvider> lockProviderMap, LockProvider defaultProvider) {
    this.defaultProvider = defaultProvider;
    this.lockProviders.putAll(lockProviderMap);
  }

  public Lock getLock(String lockKey, String lockProviderName) {

    Lock lock = lockCache.get(lockKey);
    if (lock != null) {
      return lock;
    }

    LockProvider lockProvider = defaultProvider;
    if (StringUtils.hasText(lockProviderName)) {
      lockProvider = lockProviders.get(lockProviderName);
    }

    if (lockProvider == null) {
      throw new IllegalArgumentException("Invalid lock provider name: " + lockProviderName);
    }

    lock = lockProvider.getLock(lockKey);
    Lock existingLock = lockCache.putIfAbsent(lockKey, lock);
    return existingLock != null ? existingLock : lock;
  }

  public void releaseLock(String lockKey) {
    Lock lock = lockCache.remove(lockKey);
    if (lock instanceof AutoCloseable) {
      try {
        ((AutoCloseable) lock).close();
      } catch (Exception e) {
        LOG.error("Lock: " + lockKey + ", 关闭时发生异常。");
      }
    }
  }
}