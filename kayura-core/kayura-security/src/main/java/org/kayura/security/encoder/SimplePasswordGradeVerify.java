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

package org.kayura.security.encoder;

import org.kayura.type.Result;
import org.kayura.utils.StringUtils;

/**
 * 内置的简单密码等级验证。
 * 1、密码不允许为空。
 * 2、密码长度必需大于等于 6 位。
 */
public class SimplePasswordGradeVerify implements PasswordGradeVerify {

  private int minLen = 6;

  @Override
  public Result verify(String password) {

    if (StringUtils.isBlank(password)) {
      return Result.fail("密码不允许为空。");
    }
    if (password.length() < minLen) {
      return Result.fail("密码长度必需大于等于 " + minLen + " 位。");
    }
    return Result.ok();
  }

  public void setMinLen(int minLen) {
    this.minLen = minLen;
  }
}
