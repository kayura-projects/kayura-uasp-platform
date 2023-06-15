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
