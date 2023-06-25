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