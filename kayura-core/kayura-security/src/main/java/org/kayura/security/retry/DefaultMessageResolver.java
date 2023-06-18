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
