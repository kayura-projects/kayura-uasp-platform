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
