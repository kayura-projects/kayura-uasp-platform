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

package org.kayura.spring.lock;

import org.kayura.lock.LockProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class RedisLockProvider implements LockProvider {

  private final RedisTemplate<String, String> redisTemplate;

  private static final String LOCK_PREFIX = "distributed_lock:";
  private static final long DEFAULT_LOCK_EXPIRATION = 30L; // 默认锁过期时间为30秒

  public RedisLockProvider(RedisTemplate<String, String> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @Override
  public Lock getLock(String lockKey) {
    String lockName = getLockName(lockKey);
    return new RedisLock(lockName);
  }

  private String getLockName(String lockKey) {
    return LOCK_PREFIX + lockKey;
  }

  private class RedisLock implements Lock {

    private final String lockName;

    public RedisLock(String lockName) {
      this.lockName = lockName;
    }

    @Override
    public void lock() {
      while (!tryLock()) {
        try {
          //noinspection BusyWait
          Thread.sleep(100);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
      throw new UnsupportedOperationException("lockInterruptibly is not supported for distributed lock.");
    }

    @Override
    public boolean tryLock() {
      return Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(lockName, "locked", DEFAULT_LOCK_EXPIRATION, TimeUnit.SECONDS));
    }

    @Override
    public boolean tryLock(long time, @NotNull TimeUnit unit) throws InterruptedException {
      throw new UnsupportedOperationException("tryLock with timeout is not supported for distributed lock.");
    }

    @Override
    public void unlock() {
      redisTemplate.delete(lockName);
    }

    @Override
    public Condition newCondition() {
      throw new UnsupportedOperationException("newCondition is not supported for distributed lock.");
    }
  }

}