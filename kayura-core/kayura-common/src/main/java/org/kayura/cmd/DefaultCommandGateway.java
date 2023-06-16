/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
