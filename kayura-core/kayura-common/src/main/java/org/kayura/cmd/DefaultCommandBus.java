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

import java.util.List;

public class DefaultCommandBus implements CommandBus {

  private final CommandRegistry registry;

  public DefaultCommandBus(CommandRegistry registry) {
    this.registry = registry;
  }

  public <R> R dispatch(ICommand command) {

    Class<? extends ICommand> commandClass = command.getClass();
    CommandHandlerWrapper handlerWrapper = this.registry.getHandlerWrapper(commandClass);
    List<CommandPluginWrapper> pluginWrappers = registry.getPluginWrappers();

    // plugin
    for (CommandPluginWrapper pluginWrapper : pluginWrappers) {
      R result = pluginWrapper.invoke(command, handlerWrapper);
      if (result != null) {
        return result;
      }
    }

    // handler
    if (handlerWrapper == null) {
      throw new NotCommandImplException("No handler found for " + command.getClass());
    }
    R result = handlerWrapper.invoke(command);
    return result;
  }

}
