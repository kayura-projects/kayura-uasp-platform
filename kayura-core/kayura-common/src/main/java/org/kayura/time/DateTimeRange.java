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
