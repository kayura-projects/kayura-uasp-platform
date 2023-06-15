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
