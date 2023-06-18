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
