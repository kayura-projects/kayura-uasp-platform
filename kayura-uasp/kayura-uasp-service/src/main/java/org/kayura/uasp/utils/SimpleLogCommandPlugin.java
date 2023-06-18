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
package org.kayura.uasp.utils;

import org.kayura.cmd.Command;
import org.kayura.cmd.CommandHandlerWrapper;
import org.kayura.cmd.CommandPlugin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Component;

@Component
public class SimpleLogCommandPlugin implements CommandPlugin {

  private static final Log LOG = LogFactory.getLog(SimpleLogCommandPlugin.class);

  private final ObjectMapper objectMapper;

  public SimpleLogCommandPlugin(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public <R> R invoke(Command command, CommandHandlerWrapper pluginWrapper) {

    if (LOG.isDebugEnabled()) {
      String separator = System.getProperty("line.separator");
      StringBuilder sb = new StringBuilder(separator);
      sb.append("> command: ").append(command.getClass().getName()).append(separator);
      try {
        sb.append(">        : ").append(objectMapper.writeValueAsString(command)).append(separator);
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
      sb.append("> handler: ");
      Object target = pluginWrapper.getHandler();
      if (AopUtils.isCglibProxy(target)) {
        target = AopProxyUtils.getSingletonTarget(target);
      }
      if (target != null) {
        sb.append(target.getClass().getName());
      }
      LOG.debug(sb);
    }

    return null;
  }


}
