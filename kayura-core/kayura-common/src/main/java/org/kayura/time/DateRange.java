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

import java.time.LocalDate;

public class DateRange {

  private LocalDate start;
  private LocalDate end;

  public DateRange() {
  }

  public DateRange(LocalDate start, LocalDate end) {
    this.start = start;
    this.end = end;
  }

  public static DateRange builder() {
    return new DateRange();
  }

  public static DateRange of(LocalDate start, LocalDate end) {
    return builder().setStart(start).setEnd(end);
  }

  public LocalDate getStart() {
    return start;
  }

  public DateRange setStart(LocalDate start) {
    this.start = start;
    return this;
  }

  public LocalDate getEnd() {
    return end;
  }

  public DateRange setEnd(LocalDate end) {
    this.end = end;
    return this;
  }
}
