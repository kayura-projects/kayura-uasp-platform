package org.kayura.security.sms;

import org.kayura.except.ExceptUtils;
import org.kayura.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * The type Default sms template.
 */
public class DefaultSmsTemplate implements SmsTemplate {

  private final Log logger = LogFactory.getLog(this.getClass());
  private final Map<String, String> templates;

  /**
   * Instantiates a new Default sms template.
   *
   * @param templates the templates
   */
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
