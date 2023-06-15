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

public class HighPasswordVerify extends MediumPasswordGradeVerify {

  @Override
  public Result verify(String password) {

    Result result = super.verify(password);
    if (result.isOk()) {
      if (!Pattern.compile("[A-Z]").matcher(password).find()) {
        result.setMessage("密码中必需包含大写字母。");
      }
      if (!Pattern.compile("[a-z]").matcher(password).find()) {
        result.setMessage("密码中必需包含小写字母。");
      }
      if (!Pattern.compile("[~!@#$%^&*.?]").matcher(password).find()) {
        result.setMessage("密码中必需包含一种符号 ~!@#$%^&*.? ");
      }
    }
    return result;
  }

}
