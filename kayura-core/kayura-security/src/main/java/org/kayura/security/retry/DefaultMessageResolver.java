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
