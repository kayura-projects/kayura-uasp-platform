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
