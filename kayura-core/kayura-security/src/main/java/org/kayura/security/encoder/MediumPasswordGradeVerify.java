/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
 -----------------------------------------------------------------------------*/

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
