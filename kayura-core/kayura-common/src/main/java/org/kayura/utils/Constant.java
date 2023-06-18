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
