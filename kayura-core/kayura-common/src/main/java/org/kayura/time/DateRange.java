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
