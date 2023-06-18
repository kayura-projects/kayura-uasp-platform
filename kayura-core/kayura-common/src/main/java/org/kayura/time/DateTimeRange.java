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

package org.kayura.time;

import java.time.LocalDateTime;

public class DateTimeRange {

  private LocalDateTime start;
  private LocalDateTime end;

  public DateTimeRange() {
  }

  public DateTimeRange(LocalDateTime start, LocalDateTime end) {
    this.start = start;
    this.end = end;
  }

  public static DateTimeRange builder() {
    return new DateTimeRange();
  }

  public static DateTimeRange of(LocalDateTime start, LocalDateTime end) {
    return builder().setStart(start).setEnd(end);
  }

  public LocalDateTime getStart() {
    return start;
  }

  public DateTimeRange setStart(LocalDateTime start) {
    this.start = start;
    return this;
  }

  public LocalDateTime getEnd() {
    return end;
  }

  public DateTimeRange setEnd(LocalDateTime end) {
    this.end = end;
    return this;
  }
}
