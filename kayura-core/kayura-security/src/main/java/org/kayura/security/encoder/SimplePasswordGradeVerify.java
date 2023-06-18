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
