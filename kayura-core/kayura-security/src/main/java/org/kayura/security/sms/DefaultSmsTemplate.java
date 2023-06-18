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
