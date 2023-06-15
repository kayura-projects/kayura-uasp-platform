package org.kayura.cmd;

import java.util.List;

public interface CommandRegistry {

  void addPlugin(CommandPluginWrapper pluginWrapper);

  void addInterceptor(CommandInterceptorWrapper interceptorWrapper);

  void subscribe(Class<? extends Command> commandType, CommandHandlerWrapper handlerWrapper);

  void unsubscribe(Class<? extends Command> commandClass);

  CommandHandlerWrapper getHandlerWrapper(Class<? extends Command> commandType);

  List<CommandInterceptorWrapper> getInterceptorWrappers();

  List<CommandPluginWrapper> getPluginWrappers();
}
