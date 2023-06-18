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