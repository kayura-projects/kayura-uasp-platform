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

import org.kayura.except.ExceptUtils;
import org.kayura.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class DefaultSmsTemplate implements SmsTemplate {

  private final Log logger = LogFactory.getLog(this.getClass());
  private final Map<String, String> templates;

  public DefaultSmsTemplate(Map<String, String> templates) {
    this.templates = templates;
  }

  @Override
  public String resolve(String templateId, Map<String, Object> params) {

    if (!templates.containsKey(templateId)) {
      ExceptUtils.config("系统未配置短信模版：" + templateId);
    }

    String content = templates.get(templateId);
    if (StringUtils.hasText(content)) {
      for (Map.Entry<String, Object> map : params.entrySet()) {
        final String paramName = "{" + map.getKey() + "}";
        if (content.contains(paramName)) {
          content = content.replace(paramName, map.getValue().toString());
        }
      }
    } else if (logger.isWarnEnabled()) {
      logger.warn("短信模板ID:" + templateId + ", 不存在。");
    }

    return content;
  }

}
