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
