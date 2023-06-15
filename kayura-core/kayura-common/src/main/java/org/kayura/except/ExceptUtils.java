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

package org.kayura.except;

import org.kayura.utils.StringUtils;

import java.io.IOException;

public abstract class ExceptUtils {

  public static void argument(String message) {
    throw new IllegalArgumentException(message);
  }

  public static void argument(String message, Object... params) {
    argument(StringUtils.format(message, params));
  }

  public static void business(String message) {
    throw new BusinessException(message);
  }

  public static void business(String message, Object... params) {
    business(StringUtils.format(message, params));
  }

  public static void config(String message) {
    throw new ConfigException(message);
  }

  public static void config(String message, Object... params) {
    config(StringUtils.format(message, params));
  }

  public static void io(String message) throws IOException {
    throw new IOException(message);
  }

  public static void io(String message, Object... params) throws IOException {
    io(StringUtils.format(message, params));
  }

  public static void security(String message) {
    throw new SecurityException(message);
  }

  public static void security(String message, Object... params) {
    security(StringUtils.format(message, params));
  }

}
