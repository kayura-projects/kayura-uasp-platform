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
