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