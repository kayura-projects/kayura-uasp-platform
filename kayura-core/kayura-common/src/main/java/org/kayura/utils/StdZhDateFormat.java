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

import com.fasterxml.jackson.core.io.NumberInput;

import java.text.*;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 标准的中文格式日期格式处理器。<br>
 * 可处理日期时间格式：yyyy-MM-dd HH:mm:ss 或日期格式：yyyy-MM-dd
 *
 * @author 夏亮（liangxia@live.com）
 */
public class StdZhDateFormat extends DateFormat {

  protected final static String DATETIME_FORMAT_STR_PLAIN = "yyyy-MM-dd HH:mm:ss";
  protected final static String DATE_FORMAT_STR_PLAIN = "yyyy-MM-dd";
  protected final static String[] ALL_FORMATS = new String[] { DATETIME_FORMAT_STR_PLAIN, DATE_FORMAT_STR_PLAIN };

  private final static TimeZone DEFAULT_TIMEZONE;
  private final static Locale DEFAULT_LOCALE = Locale.CHINA;

  protected final static DateFormat DATETIME_FORMAT_PLAIN;
  protected final static DateFormat DATE_FORMAT_PLAIN;

  static {

    DEFAULT_TIMEZONE = TimeZone.getTimeZone("UTC");

    DATETIME_FORMAT_PLAIN = new SimpleDateFormat(DATETIME_FORMAT_STR_PLAIN, DEFAULT_LOCALE);
    DATETIME_FORMAT_PLAIN.setTimeZone(DEFAULT_TIMEZONE);

    DATE_FORMAT_PLAIN = new SimpleDateFormat(DATE_FORMAT_STR_PLAIN, DEFAULT_LOCALE);
    DATE_FORMAT_PLAIN.setTimeZone(DEFAULT_TIMEZONE);
  }

  public final static StdZhDateFormat instance = new StdZhDateFormat();

  protected final Locale _locale;
  protected Boolean _lenient;

  protected transient TimeZone _timezone;
  protected transient DateFormat _formatDateTimePlain;
  protected transient DateFormat _formatDatePlain;

  public StdZhDateFormat() {
    _locale = DEFAULT_LOCALE;
  }

  protected StdZhDateFormat(TimeZone tz, Locale loc, Boolean lenient) {
    _timezone = tz;
    _locale = loc;
    _lenient = lenient;
  }

  public static TimeZone getDefaultTimeZone() {
    return DEFAULT_TIMEZONE;
  }

  public StdZhDateFormat withTimeZone(TimeZone tz) {
    if (tz == null) {
      tz = DEFAULT_TIMEZONE;
    }
    if ((tz == _timezone) || tz.equals(_timezone)) {
      return this;
    }
    return new StdZhDateFormat(tz, _locale, _lenient);
  }

  public StdZhDateFormat withLocale(Locale loc) {
    if (loc.equals(_locale)) {
      return this;
    }
    return new StdZhDateFormat(_timezone, loc, _lenient);
  }

  @Override
  public StdZhDateFormat clone() {
    return new StdZhDateFormat(_timezone, _locale, _lenient);
  }

  @Override
  public void setTimeZone(TimeZone tz) {

    if (!tz.equals(_timezone)) {
      _clearFormats();
      _timezone = tz;
    }
  }

  @Override
  public void setLenient(boolean enabled) {
    Boolean newValue = enabled;
    if (_lenient != newValue) {
      _lenient = newValue;
      _clearFormats();
    }
  }

  @Override
  public boolean isLenient() {
    if (_lenient == null) {
      return true;
    }
    return _lenient.booleanValue();
  }

  @Override
  public Date parse(String dateStr) throws ParseException {

    dateStr = dateStr.trim();
    ParsePosition pos = new ParsePosition(0);
    Date dt = null;

    if (looksLikeDate(dateStr)) {
      dt = parseAsDate(dateStr, pos, true);
    }

    if (dt != null) {
      return dt;
    }

    StringBuilder sb = new StringBuilder();
    for (String f : ALL_FORMATS) {
      if (sb.length() > 0) {
        sb.append("\", \"");
      } else {
        sb.append('"');
      }
      sb.append(f);
    }
    sb.append('"');
    throw new ParseException(String.format("Can not parse date \"%s\": not compatible with any of standard forms (%s)",
        dateStr, sb.toString()), pos.getErrorIndex());
  }

  @Override
  public Date parse(String dateStr, ParsePosition pos) {

    if (looksLikeDate(dateStr)) {
      try {
        return parseAsDate(dateStr, pos, false);
      } catch (ParseException e) {
        return null;
      }
    }

    int i = dateStr.length();
    while (--i >= 0) {
      char ch = dateStr.charAt(i);
      if (ch < '0' || ch > '9') {
        if (i > 0 || ch != '-') {
          break;
        }
      }
    }
    if (i < 0) {
      if (dateStr.charAt(0) == '-' || NumberInput.inLongRange(dateStr, false)) {
        return new Date(Long.parseLong(dateStr));
      }
    }
    try {
      return parseAsDate(dateStr, pos, false);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {

    if (_formatDateTimePlain == null) {
      _formatDateTimePlain = _cloneFormat(DATETIME_FORMAT_PLAIN, DATETIME_FORMAT_STR_PLAIN, _timezone, _locale,
          _lenient);
    }
    return _formatDateTimePlain.format(date, toAppendTo, fieldPosition);
  }

  @Override
  public String toString() {
    String str = "DateFormat " + getClass().getName();
    TimeZone tz = _timezone;
    if (tz != null) {
      str += " (timezone: " + tz + ")";
    }
    str += "(locale: " + _locale + ")";
    return str;
  }

  @Override
  public boolean equals(Object o) {
    return (o == this);
  }

  @Override
  public int hashCode() {
    return System.identityHashCode(this);
  }

  protected boolean looksLikeDate(String dateStr) {
    return dateStr.length() >= 5 && Character.isDigit(dateStr.charAt(0)) && Character.isDigit(dateStr.charAt(3))
        && dateStr.charAt(4) == '-';
  }

  protected Date parseAsDate(String dateStr, ParsePosition pos, boolean throwErrors) throws ParseException {

    int len = dateStr.length();
    char c = dateStr.charAt(len - 1);
    DateFormat df;
    String formatStr;

    if (len <= 10 && Character.isDigit(c)) {
      df = _formatDatePlain;
      formatStr = DATE_FORMAT_STR_PLAIN;
      if (df == null) {
        df = _formatDatePlain = _cloneFormat(DATE_FORMAT_PLAIN, formatStr, _timezone, _locale, _lenient);
      }
    } else {
      df = _formatDateTimePlain;
      formatStr = DATETIME_FORMAT_STR_PLAIN;
      if (df == null) {
        df = _formatDateTimePlain = _cloneFormat(DATETIME_FORMAT_PLAIN, formatStr, _timezone, _locale, _lenient);
      }
    }

    Date dt = df.parse(dateStr, pos);
    if (dt == null) {
      throw new ParseException(
          String.format("Can not parse date \"%s\": while it seems to fit format '%s', parsing fails (leniency? %s)",
              dateStr, formatStr, _lenient),
          pos.getErrorIndex());
    }
    return dt;
  }

  private final static DateFormat _cloneFormat(DateFormat df, String format, TimeZone tz, Locale loc, Boolean lenient) {

    if (!loc.equals(DEFAULT_LOCALE)) {
      df = new SimpleDateFormat(format, loc);
      df.setTimeZone((tz == null) ? DEFAULT_TIMEZONE : tz);
    } else {
      df = (DateFormat) df.clone();
      if (tz != null) {
        df.setTimeZone(tz);
      }
    }
    if (lenient != null) {
      df.setLenient(lenient.booleanValue());
    }
    return df;
  }

  protected void _clearFormats() {
    _formatDateTimePlain = null;
    _formatDatePlain = null;
  }
}
