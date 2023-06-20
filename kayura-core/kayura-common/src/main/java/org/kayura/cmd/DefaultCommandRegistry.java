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

package org.kayura.cmd;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class DefaultCommandRegistry implements CommandRegistry {

  private static final Log LOG = LogFactory.getLog(DefaultCommandRegistry.class);
  private final Map<Class<?>, CommandHandlerWrapper> handlerWrappers = new ConcurrentHashMap<>();
  private final List<CommandInterceptorWrapper> interceptorWrappers = new CopyOnWriteArrayList<>();
  private final List<CommandPluginWrapper> pluginWrappers = new CopyOnWriteArrayList<>();

  @Override
  public void addPlugin(CommandPluginWrapper pluginWrapper) {
    this.pluginWrappers.add(pluginWrapper);
  }

  @Override
  public void addInterceptor(CommandInterceptorWrapper interceptorWrapper) {
    this.interceptorWrappers.add(interceptorWrapper);
  }

  public void subscribe(Class<? extends ICommand> commandType, CommandHandlerWrapper handlerWrapper) {

    CommandHandlerWrapper oldValue = handlerWrappers.put(commandType, handlerWrapper);
    if (oldValue != null) {
      if (LOG.isInfoEnabled()) {
        LOG.info(oldValue.getHandler().getClass().getName() + " Command is replaced with " + handlerWrapper.getHandler().getClass().getName());
      }
    } else {
      if (LOG.isTraceEnabled()) {
        LOG.trace("New command -> " + handlerWrapper.getHandler().getClass().getName());
      }
    }
  }

  public void unsubscribe(Class<? extends ICommand> commandClass) {
    handlerWrappers.remove(commandClass);
  }

  @Override
  public CommandHandlerWrapper getHandlerWrapper(Class<? extends ICommand> commandType) {
    return this.handlerWrappers.getOrDefault(commandType, null);
  }

  @Override
  public List<CommandInterceptorWrapper> getInterceptorWrappers() {
    return this.interceptorWrappers;
  }

  @Override
  public List<CommandPluginWrapper> getPluginWrappers() {
    return this.pluginWrappers;
  }

}