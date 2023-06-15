package org.kayura.time;

import java.time.LocalTime;

public class TimeRange {

  private LocalTime start;
  private LocalTime end;

  public TimeRange() {
  }

  public TimeRange(LocalTime start, LocalTime end) {
    this.start = start;
    this.end = end;
  }

  public static TimeRange builder() {
    return new TimeRange();
  }


  public LocalTime getStart() {
    return start;
  }

  public TimeRange setStart(LocalTime start) {
    this.start = start;
    return this;
  }

  public LocalTime getEnd() {
    return end;
  }

  public TimeRange setEnd(LocalTime end) {
    this.end = end;
    return this;
  }
}
