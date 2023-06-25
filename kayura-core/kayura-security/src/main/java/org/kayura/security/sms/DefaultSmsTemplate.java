/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
