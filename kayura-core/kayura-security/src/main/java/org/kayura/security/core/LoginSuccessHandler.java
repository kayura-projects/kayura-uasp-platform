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
