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
package org.kayura.uasp.utils;

import org.kayura.cmd.CommandHandlerWrapper;
import org.kayura.cmd.CommandPlugin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kayura.cmd.ICommand;
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
  public <R> R invoke(ICommand command, CommandHandlerWrapper pluginWrapper) {

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
