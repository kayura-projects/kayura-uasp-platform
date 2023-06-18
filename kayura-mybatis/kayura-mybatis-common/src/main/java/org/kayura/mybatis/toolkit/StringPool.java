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

package org.kayura.mybatis.toolkit;

public interface StringPool {

  String AMPERSAND = "&";
  String AND = "and";
  String AT = "@";
  String ASTERISK = "*";
  String STAR = ASTERISK;
  String BACK_SLASH = "\\";
  String COLON = ":";
  String COMMA = ",";
  String DASH = "-";
  String DOLLAR = "$";
  String DOT = ".";
  String DOTDOT = "..";
  String DOT_CLASS = ".class";
  String DOT_JAVA = ".java";
  String DOT_XML = ".xml";
  String EMPTY = "";
  String EQUALS = "=";
  String FALSE = "false";
  String SLASH = "/";
  String HASH = "#";
  String HAT = "^";
  String LEFT_BRACE = "{";
  String LEFT_BRACKET = "(";
  String LEFT_CHEV = "<";
  String DOT_NEWLINE = ",\n";
  String NEWLINE = "\n";
  String N = "n";
  String NO = "no";
  String NULL = "null";
  String OFF = "off";
  String ON = "on";
  String PERCENT = "%";
  String PIPE = "|";
  String PLUS = "+";
  String QUESTION_MARK = "?";
  String EXCLAMATION_MARK = "!";
  String QUOTE = "\"";
  String RETURN = "\r";
  String TAB = "\t";
  String RIGHT_BRACE = "}";
  String RIGHT_BRACKET = ")";
  String RIGHT_CHEV = ">";
  String SEMICOLON = ";";
  String SINGLE_QUOTE = "'";
  String BACKTICK = "`";
  String SPACE = " ";
  String TILDA = "~";
  String LEFT_SQ_BRACKET = "[";
  String RIGHT_SQ_BRACKET = "]";
  String TRUE = "true";
  String UNDERSCORE = "_";
  String UTF_8 = "UTF-8";
  String US_ASCII = "US-ASCII";
  String ISO_8859_1 = "ISO-8859-1";
  String Y = "y";
  String YES = "yes";
  String ONE = "1";
  String ZERO = "0";
  String DOLLAR_LEFT_BRACE = "${";
  String HASH_LEFT_BRACE = "#{";
  String CRLF = "\r\n";

  String HTML_NBSP = "&nbsp;";
  String HTML_AMP = "&amp";
  String HTML_QUOTE = "&quot;";
  String HTML_LT = "&lt;";
  String HTML_GT = "&gt;";

  // ---------------------------------------------------------------- array

  String[] EMPTY_ARRAY = new String[0];

  byte[] BYTES_NEW_LINE = StringPool.NEWLINE.getBytes();
}
