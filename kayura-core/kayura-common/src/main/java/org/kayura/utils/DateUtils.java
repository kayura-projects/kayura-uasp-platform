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

package org.kayura.utils;

import org.kayura.time.DateRange;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class DateUtils {

  public static final String YYYYMMDD = "yyyy-MM-dd";
  public static final String HHMMSS = "HH:mm:ss";
  public static final String YYYYMMDDHHMMSS = YYYYMMDD + " " + HHMMSS;

  public static LocalDate nowDate() {
    return LocalDate.now();
  }

  public static LocalTime nowTime() {
    return LocalTime.now();
  }

  public static LocalDateTime now() {
    return LocalDateTime.now();
  }

  public static String formatDate(LocalDate date) {
    return format(date, YYYYMMDD);
  }

  public static String formatDate(LocalDateTime date) {
    return format(date, YYYYMMDD);
  }

  public static String formatDateTime(LocalDateTime date) {
    return format(date, YYYYMMDDHHMMSS);
  }

  public static String formatTime(LocalDateTime date) {
    return format(date, HHMMSS);
  }

  public static String formatTime(LocalTime date) {
    return format(date, HHMMSS);
  }

  public static String format(LocalDate date, String format) {
    assert date != null;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return date.format(formatter);
  }

  public static String format(LocalDateTime dateTime, String format) {
    assert dateTime != null;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return dateTime.format(formatter);
  }

  public static String format(LocalTime time, String format) {
    assert time != null;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return time.format(formatter);
  }

  public static String formatNow(String format) {
    assert format != null;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return now().format(formatter);
  }

  public static String formatNowDate() {
    return formatNow(YYYYMMDD);
  }

  public static String formatNowDateTime() {
    return formatNow(YYYYMMDDHHMMSS);
  }

  public static String formatNowTime() {
    return formatNow(HHMMSS);
  }

  public static Date toDate(LocalDateTime localDateTime) {
    assert localDateTime != null;
    ZoneId zoneId = ZoneId.systemDefault();
    ZonedDateTime zdt = localDateTime.atZone(zoneId);
    return Date.from(zdt.toInstant());
  }

  public static LocalDate toLocalDate(Date date) {
    assert date != null;
    ZoneId zoneId = ZoneId.systemDefault();
    Instant instant = date.toInstant();
    LocalDate localDate = instant.atZone(zoneId).toLocalDate();
    return localDate;
  }

  public static LocalDateTime toLocalDateTime(Date date) {
    assert date != null;
    ZoneId zoneId = ZoneId.systemDefault();
    Instant instant = date.toInstant();
    LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
    return localDateTime;
  }

  public static int thisYear() {
    return nowDate().getYear();
  }

  public static DateRange thisYearRange() {
    LocalDate today = nowDate();
    return DateRange.builder()
      .setStart(today.with(TemporalAdjusters.firstDayOfYear()))
      .setEnd(today.with(TemporalAdjusters.lastDayOfYear()));
  }

  public static LocalDate firstDayOfYear() {
    return nowDate().with(TemporalAdjusters.firstDayOfYear());
  }

  public static LocalDate lastDayOfYear() {
    return nowDate().with(TemporalAdjusters.lastDayOfYear());
  }

  public static int thisMonth() {
    return nowDate().getMonthValue();
  }

  public static DateRange thisMonthRange() {
    LocalDate today = nowDate();
    return DateRange.builder()
      .setStart(today.with(TemporalAdjusters.firstDayOfMonth()))
      .setEnd(today.with(TemporalAdjusters.lastDayOfMonth()));
  }

  public static LocalDate firstDayOfMonth() {
    return nowDate().with(TemporalAdjusters.firstDayOfMonth());
  }

  public static LocalDate lastDayOfMonth() {
    return nowDate().with(TemporalAdjusters.lastDayOfMonth());
  }

  public static String friendly(Duration duration) {
    StringBuilder sb = new StringBuilder();
    if (duration.toDays() > 0) {
      sb.append(duration.toDays()).append("天");
      duration = duration.minusDays(duration.toDays());
    }
    if (duration.toHours() > 0) {
      sb.append(duration.toHours()).append("时");
      duration = duration.minusHours(duration.toHours());
    }
    if (duration.toMinutes() > 0) {
      sb.append(duration.toMinutes()).append("分");
      duration = duration.minusMinutes(duration.toMinutes());
    }
    if (duration.getSeconds() > 0) {
      sb.append(duration.getSeconds()).append("秒");
    }
    return sb.toString();
  }

  public static long longPlusSeconds(int seconds) {

    long nextTime = now().plusSeconds(seconds).toEpochSecond(ZoneOffset.ofHours(8));
    return nextTime;
  }

}
