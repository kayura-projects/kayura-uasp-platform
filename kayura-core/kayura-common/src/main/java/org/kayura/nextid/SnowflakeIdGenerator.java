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

package org.kayura.nextid;

/**
 * Witter 开源的一款分布式自增ID算法 snowflake
 *
 * @author liangxia@live.com
 */
@SuppressWarnings("ALL")
public class SnowflakeIdGenerator implements IdGenerator {

  public static final SnowflakeIdGenerator INSTANCE = new SnowflakeIdGenerator();

  private final long twepoch = 1288834974657L;
  private final long workerIdBits = 5L;
  private final long datacenterIdBits = 5L;
  private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
  private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
  private final long sequenceBits = 12L;
  private final long workerIdShift = sequenceBits;
  private final long datacenterIdShift = sequenceBits + workerIdBits;
  private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
  private final long sequenceMask = -1L ^ (-1L << sequenceBits);

  private final long workerId;
  private final long datacenterId;
  private long sequence = 0L;
  private long lastTimestamp = -1L;

  public SnowflakeIdGenerator() {
    this(1, 1);
  }

  public SnowflakeIdGenerator(long workerId, long datacenterId) {

    if (workerId > maxWorkerId || workerId < 0) {
      throw new IllegalArgumentException(
        String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
    }
    if (datacenterId > maxDatacenterId || datacenterId < 0) {
      throw new IllegalArgumentException(
        String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
    }
    this.workerId = workerId;
    this.datacenterId = datacenterId;
  }

  @Override
  public synchronized String nextId() {
    long timestamp = timeGen();
    if (timestamp < lastTimestamp) {
      // 服务器时钟被调整了,ID生成器停止服务.
      throw new RuntimeException(String.format(
        "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
    }
    if (lastTimestamp == timestamp) {
      sequence = (sequence + 1) & sequenceMask;
      if (sequence == 0) {
        timestamp = tilNextMillis(lastTimestamp);
      }
    } else {
      sequence = 0L;
    }

    lastTimestamp = timestamp;
    Long newValue = ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
      | (workerId << workerIdShift) | sequence;

    return String.valueOf(newValue);
  }

  protected long tilNextMillis(long lastTimestamp) {
    long timestamp = timeGen();
    while (timestamp <= lastTimestamp) {
      timestamp = timeGen();
    }
    return timestamp;
  }

  protected long timeGen() {
    return System.currentTimeMillis();
  }

}
