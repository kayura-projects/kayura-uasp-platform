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