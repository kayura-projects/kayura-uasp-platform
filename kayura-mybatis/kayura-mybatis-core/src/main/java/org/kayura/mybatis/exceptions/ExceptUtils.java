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

package org.kayura.mybatis.exceptions;

import org.kayura.utils.StringUtils;

public class ExceptUtils {

  public static IBatisException except(String msg, Throwable t, Object... params) {
    return new IBatisException(StringUtils.format(msg, params), t);
  }

  /**
   * 重载的方法
   *
   * @param msg 消息
   * @return 返回异常
   */
  public static IBatisException except(String msg, Object... params) {
    return new IBatisException(StringUtils.format(msg, params));
  }

  /**
   * 重载的方法
   *
   * @param t 异常
   * @return 返回异常
   */
  public static IBatisException except(Throwable t) {
    return new IBatisException(t);
  }

}
