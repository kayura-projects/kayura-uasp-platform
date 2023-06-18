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
