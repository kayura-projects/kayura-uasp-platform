package org.kayura.security.retry;

public class StoreRetry {

  private int used;
  private long nextTime;

  public static StoreRetry builder() {
    return new StoreRetry();
  }

  public static StoreRetry of(String value) {

    StoreRetry retry = builder();
    String[] split = value.split(",");
    retry.used = Integer.parseInt(split[0]);
    retry.nextTime = Long.parseLong(split[1]);
    return retry;
  }

  public int getUsed() {
    return used;
  }

  public StoreRetry setUsed(int used) {
    this.used = used;
    return this;
  }

  public long getNextTime() {
    return nextTime;
  }

  public StoreRetry setNextTime(long nextTime) {
    this.nextTime = nextTime;
    return this;
  }
}
