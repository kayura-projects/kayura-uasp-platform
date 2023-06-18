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

package org.kayura.cmd;

import java.util.List;

public class DefaultCommandBus implements CommandBus {

  private final CommandRegistry registry;

  public DefaultCommandBus(CommandRegistry registry) {
    this.registry = registry;
  }

  public <R> R dispatch(Command command) {

    Class<? extends Command> commandClass = command.getClass();
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
