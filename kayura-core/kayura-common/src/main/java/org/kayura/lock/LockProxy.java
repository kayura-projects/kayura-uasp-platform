/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
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