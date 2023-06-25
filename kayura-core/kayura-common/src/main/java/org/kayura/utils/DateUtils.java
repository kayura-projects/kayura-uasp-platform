/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
