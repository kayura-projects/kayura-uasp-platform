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
