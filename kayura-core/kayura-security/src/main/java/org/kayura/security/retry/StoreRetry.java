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
