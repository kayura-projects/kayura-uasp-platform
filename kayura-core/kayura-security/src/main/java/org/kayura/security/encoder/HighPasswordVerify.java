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
