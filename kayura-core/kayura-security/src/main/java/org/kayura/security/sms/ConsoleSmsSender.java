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

package org.kayura.security.sms;

import org.kayura.utils.StringUtils;

import java.util.List;
import java.util.Map;

public class ConsoleSmsSender implements SmsSender {

  private final SmsTemplate template;

  public ConsoleSmsSender(SmsTemplate template) {
    this.template = template;
  }

  @Override
  public String getPlatform() {
    return "Console";
  }

  @Override
  public void sendSms(String templateId, List<String> mobiles, Map<String, Object> params) {

    String content = this.template.resolve(templateId, params);
    if (StringUtils.hasText(content)) {
      System.out.println("发送短信至：" + String.join(",", mobiles) + "。内容：" + content);
    }
  }

}
