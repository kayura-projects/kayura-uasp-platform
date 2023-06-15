/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ 版权所属 2019 Liang.Xia 及 原作者
 ~ 您可以在 Apache License 2.0 版下获得许可副本，
 ~ 同时必须保证分发的本软件是在“原始”的基础上分发的。
 ~ 除非适用法律要求或书面同意。
 ~
 ~ http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ 请参阅许可证中控制权限和限制的特定语言。
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

package org.kayura.mybatis.toolkit;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * The type String kit.
 */
public abstract class StringKit extends StringUtils implements StringPool {

  /**
   * The constant EMPTY.
   */
  public static final String EMPTY = "";
  /**
   * The constant IS.
   */
  public static final String IS = "is";
  /**
   * The constant UNDERLINE.
   */
  public static final char UNDERLINE = '_';
  /**
   * The constant PLACE_HOLDER.
   */
  public static final String PLACE_HOLDER = "{%s}";
  /**
   * The constant MP_SQL_PLACE_HOLDER.
   */
  public static final Pattern MP_SQL_PLACE_HOLDER = Pattern.compile("[{](?<idx>\\d+)}");
  /**
   * The constant P_IS_COLUMN.
   */
  public static final Pattern P_IS_COLUMN = Pattern.compile("^\\w\\S*[\\w\\d]*$");
  /**
   * The constant CAPITAL_MODE.
   */
  public static final Pattern CAPITAL_MODE = Pattern.compile("^[0-9A-Z/_]+$");

  /**
   * Format string.
   *
   * @param target the target
   * @param params the params
   * @return the string
   */
  public static String format(String target, Object... params) {
    if (target.contains("%s") && ArrayUtils.isNotEmpty(params)) {
      return String.format(target, params);
    }
    return target;
  }

  /**
   * 字符串驼峰(java属性)转下划线格式(数据库字段).
   *
   * <pre>
   * id         = id_
   * userId     = user_id_
   * </pre>
   *
   * @param param the param
   * @return string string
   */
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

  /**
   * 字符串下划线(数据库字段)转驼峰(java属性)格式。
   *
   * <pre>
   * id_          = id
   * user_id_      = userId
   * </pre>
   *
   * @param param the param
   * @return string
   */
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

  /**
   * 将实体名转为表名
   *
   * <pre>
   * Table          = table
   * SimpleTable    = simple_table
   * </pre>
   *
   * @param param the param
   * @return string
   */
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

  /**
   * 将实体名转为驼峰格式。
   *
   * <pre>
   * Table          = table
   * SimpleTable    = simpleTable
   * </pre>
   *
   * @param param the param
   * @return string
   */
  public static String classToCamel(String param) {
    if (isBlank(param)) {
      return EMPTY;
    }
    return param.substring(0, 1).toLowerCase() + param.substring(1);
  }

  /**
   * 将表名转换为实体名
   *
   * <pre>
   * table          = Table
   * simple_table   = SimpleTable
   * </pre>
   *
   * @param param the param
   * @return string
   */
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

  /**
   * 将数据表名转为驼峰格式。
   *
   * <pre>
   * table          = table
   * simple_table   = simpleTable
   * </pre>
   *
   * @param param the param
   * @return string
   */
  public static String tableToCamel(String param) {
    return classToCamel(tableToClass(param));
  }

  /**
   * Has length boolean.
   *
   * @param str the str
   * @return the boolean
   */
  public static boolean hasLength(String str) {
    return (str != null && !str.isEmpty());
  }

  /**
   * Has text boolean.
   *
   * @param str the str
   * @return the boolean
   */
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

  /**
   * Value not null boolean.
   *
   * @param value the value
   * @return the boolean
   */
  public static boolean valueNotNull(Object value) {
    if (value instanceof CharSequence) {
      return isNotEmpty((CharSequence) value);
    }
    return value != null;
  }

  /**
   * Value null boolean.
   *
   * @param value the value
   * @return the boolean
   */
  public static boolean valueNull(Object value) {
    return !valueNotNull(value);
  }

  /**
   * Concat capitalize string.
   *
   * @param concatStr the concat str
   * @param str       the str
   * @return the string
   */
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
