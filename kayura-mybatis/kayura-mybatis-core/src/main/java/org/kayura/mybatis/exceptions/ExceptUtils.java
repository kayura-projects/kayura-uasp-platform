/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ 版权所属 2019 Liang.Xia 及 原作者
 ~ 您可以在 Apache License 2.0 版下获得许可副本，
 ~ 同时必须保证分发的本软件是在“原始”的基础上分发的。
 ~ 除非适用法律要求或书面同意。
 ~
 ~ http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ 请参阅许可证中控制权限和限制的特定语言。
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

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
