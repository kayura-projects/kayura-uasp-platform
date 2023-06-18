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

package org.kayura.security.core;

import org.kayura.security.LoginUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录成功处理器接口。
 */
public interface LoginSuccessHandler {

  /**
   * 处理登录成功的操作。
   *
   * @param request   HTTP 请求对象。
   * @param response  HTTP 响应对象。
   * @param loginUser 登录成功的用户对象。
   */
  void handle(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser);

}
