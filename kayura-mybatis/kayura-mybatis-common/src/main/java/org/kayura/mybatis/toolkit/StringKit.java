/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.mybatis.toolkit;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public abstract class StringKit extends StringUtils implements StringPool {

  public static final String EMPTY = "";
  public static final String IS = "is";
  public static final char UNDERLINE = '_';
  public static final String PLACE_HOLDER = "{%s}";
  public static final Pattern MP_SQL_PLACE_HOLDER = Pattern.compile("[{](?<idx>\\d+)}");
  public static final Pattern P_IS_COLUMN = Pattern.compile("^\\w\\S*[\\w\\d]*$");
  public static final Pattern CAPITAL_MODE = Pattern.compile("^[0-9A-Z/_]+$");

  public static String format(String target, Object... params) {
    if (target.contains("%s") && ArrayUtils.isNotEmpty(params)) {
      return String.format(target, params);
    }
    return target;
  }

  public static String camelToColumn(String param) {
    if (isBlank(param)) {
      return EMPTY;
    }
    int len = param.length();
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      char c = param.charAt(i);
      if (Character.isUpperCase(c) && i > 0) {
        sb.append(UNDERLINE);
      }
      sb.append(Character.toLowerCase(c));
    }
    return sb.append(UNDERLINE).toString();
  }

  public static String columnToCamel(String param) {
    if (isBlank(param)) {
      return EMPTY;
    }
    String temp = param.toLowerCase();
    int len = temp.length();
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      char c = temp.charAt(i);
      if (c == UNDERLINE) {
        if (++i < len) {
          sb.append(Character.toUpperCase(temp.charAt(i)));
        }
      } else {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  public static String classToTable(String param) {

    if (isBlank(param)) {
      return EMPTY;
    }
    int len = param.length();
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      char c = param.charAt(i);
      if (Character.isUpperCase(c) && i > 0) {
        sb.append(UNDERLINE);
      }
      sb.append(Character.toLowerCase(c));
    }
    return sb.toString();
  }

  public static String classToCamel(String param) {
    if (isBlank(param)) {
      return EMPTY;
    }
    return param.substring(0, 1).toLowerCase() + param.substring(1);
  }

  public static String tableToClass(String param) {

    if (isBlank(param)) {
      return EMPTY;
    }
    String temp = param.toLowerCase();
    int len = temp.length();
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      char c = temp.charAt(i);
      if (c == UNDERLINE) {
        if (++i < len) {
          sb.append(Character.toUpperCase(temp.charAt(i)));
        }
      } else {
        if (i == 0) {
          sb.append(Character.toUpperCase(c));
        } else {
          sb.append(c);
        }
      }
    }
    return sb.toString();
  }

  public static String tableToCamel(String param) {
    return classToCamel(tableToClass(param));
  }

  public static boolean hasLength(String str) {
    return (str != null && !str.isEmpty());
  }

  public static boolean hasText(CharSequence str) {
    return (str != null && str.length() > 0 && containsText(str));
  }

  private static boolean containsText(CharSequence str) {
    int strLen = str.length();
    for (int i = 0; i < strLen; i++) {
      if (!Character.isWhitespace(str.charAt(i))) {
        return true;
      }
    }
    return false;
  }

  public static boolean valueNotNull(Object value) {
    if (value instanceof CharSequence) {
      return isNotEmpty((CharSequence) value);
    }
    return value != null;
  }

  public static boolean valueNull(Object value) {
    return !valueNotNull(value);
  }

  public static String concatCapitalize(String concatStr, final String str) {
    if (isEmpty(concatStr)) {
      concatStr = EMPTY;
    }
    if (str == null || str.length() == 0) {
      return str;
    }
    final char firstChar = str.charAt(0);
    if (Character.isTitleCase(firstChar)) {
      return str;
    }
    return concatStr + Character.toTitleCase(firstChar) + str.substring(1);
  }

}
