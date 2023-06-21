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

package org.kayura.uasp.utils;

public interface UaspConsts extends CacheConsts, SecurityConsts, TipConsts {

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
