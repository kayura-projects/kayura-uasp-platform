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

public class DefaultCommandGateway implements CommandGateway {

  private final CommandRegistry registry;
  private final CommandBus commandBus;

  public DefaultCommandGateway(CommandRegistry registry, CommandBus commandBus) {
    this.registry = registry;
    this.commandBus = commandBus;
  }

  public <R> R send(Command command) {

    Class<? extends Command> commandClass = command.getClass();
    List<CommandInterceptorWrapper> interceptorWrappers = registry.getInterceptorWrappers()
      .stream().filter(x -> x.hasSupport(commandClass)).toList();
    if (!interceptorWrappers.isEmpty()) {
      CommandSession session = CommandSession.create(command);
      try {
        interceptorWrappers.forEach(w -> w.invokeBefore(session));
        R result = commandBus.dispatch(command);
        session.setResult(result);
        return result;
      } finally {
        interceptorWrappers.forEach(w -> w.invokeAfter(session));
      }
    } else {
      R result = commandBus.dispatch(command);
      return result;
    }
  }
}
