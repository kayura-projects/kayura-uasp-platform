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

package org.kayura.uasp.passport;

public class SmsCaptchaFrm {

  private String mobile;
  private String captcha;

  public String getMobile() {
    return mobile;
  }

  public SmsCaptchaFrm setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public String getCaptcha() {
    return captcha;
  }

  public SmsCaptchaFrm setCaptcha(String captcha) {
    this.captcha = captcha;
    return this;
  }
}
