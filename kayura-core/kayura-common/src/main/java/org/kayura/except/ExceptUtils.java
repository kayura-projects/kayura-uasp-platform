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
