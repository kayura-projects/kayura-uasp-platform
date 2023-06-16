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

package org.kayura.uasp.utils;

public interface UaspConstants {

  String USER_TYPE_ROOT = "UT_ROOT";
  String USER_TYPE_ADMIN = "UT_ADMIN";
  String USER_TYPE_MANAGER = "UT_MANAGER";
  String USER_TYPE_USER = "UT_USER";
  String USER_TYPE_CLIENT = "UT_CLIENT";

  String UASP_APP_ID = "UASP";
  String UASP_APP_CODE = UASP_APP_ID;

  String GLOBAL = "GLOBAL";

  // 数据词典：数组架构公司分类
  String UASP_ORG_CATEGORY = "UASP_ORG_CATEGORY";
  String UASP_FUNC_ACTION = "UASP_FUNC_ACTION";

  // UASP 内置短信类型
  String SMS_REGISTER = "REGISTER";

}
