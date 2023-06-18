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
