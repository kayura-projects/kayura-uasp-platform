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

package org.kayura.security.retry;

import org.kayura.utils.DateUtils;

import java.time.Duration;

public class DefaultMessageResolver implements MessageResolver {

  @Override
  public String resolve(RetryResult result) {

    StringBuilder sb = new StringBuilder();
    if (result.isLocked()) {
      sb.append("登录尝试过多，账号已被锁定。");
    } else {
      if (result.getWait() > 0) {
        sb.append("第").append(result.getUsed()).append("次密码错误");
        Duration duration = Duration.ofSeconds(result.getWait());
        sb.append("，").append(DateUtils.friendly(duration)).append("后可再次登录。");
      } else {
        sb.append("输入的密码不正确。");
      }
    }
    return sb.toString();
  }

}
