package org.kayura.security.retry;

public class RetryResult {

  private int used;
  private int max;
  private int wait;
  private boolean locked;
  private String message;

  public static RetryResult builder() {
    return new RetryResult();
  }

  public int getUsed() {
    return used;
  }

  public RetryResult setUsed(int used) {
    this.used = used;
    return this;
  }

  public int getWait() {
    return wait;
  }

  public RetryResult setWait(int wait) {
    this.wait = wait;
    return this;
  }

  public boolean isLocked() {
    return locked;
  }

  public RetryResult setLocked(boolean locked) {
    this.locked = locked;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public RetryResult setMessage(String message) {
    this.message = message;
    return this;
  }

  public int getMax() {
    return max;
  }

  public RetryResult setMax(int max) {
    this.max = max;
    return this;
  }
}
