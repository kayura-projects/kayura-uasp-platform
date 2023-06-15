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

import java.util.regex.Pattern;

/**
 * 内置的中等级密码等级检查
 * 1、密码不允许为空。
 * 2、密码长度必需大于等于 8 位。
 * 3、密码中必需包含字母。
 * 4、密码中必需包含数字。
 */
public class MediumPasswordGradeVerify extends SimplePasswordGradeVerify {

  public MediumPasswordGradeVerify() {
    this.setMinLen(8);
  }

  @Override
  public Result verify(String password) {

    Result result = super.verify(password);
    if (result.isOk()) {
      if (!Pattern.compile("[a-zA-Z]").matcher(password).find()) {
        result.setMessage("密码中必需包含字母。");
      }
      if (!Pattern.compile("[0-9]").matcher(password).find()) {
        result.setMessage("密码中必需包含数字。");
      }
    }
    return result;
  }
}
