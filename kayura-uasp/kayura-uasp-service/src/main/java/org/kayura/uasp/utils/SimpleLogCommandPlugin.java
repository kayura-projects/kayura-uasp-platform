/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
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
