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
