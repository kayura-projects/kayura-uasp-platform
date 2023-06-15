/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
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
