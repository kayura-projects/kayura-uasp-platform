/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
 -----------------------------------------------------------------------------*/

package org.kayura.utils;

public interface Constant {

  // -- Pattern --
  String MOBILE = "^1[3-9]\\d{9}$";
  String EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
  String CODE = "^[0-9a-zA-Z_]+$";
  String NORMAL_CHAR = "^[\\u4E00-\\u9FA5A-Za-z0-9_]+$";
  String URL = "[a-zA-z]+://[^\\s]*";

  // -- DataTime --
  String DATE = "yyyy-MM-dd";
  String TIME = "HH:mm:ss";
  String DATE_TIME = DATE + " " + TIME;

  // -- boolean --
  String TRUE = "true";
  String FALSE = "false";

}
