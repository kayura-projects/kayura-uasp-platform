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
