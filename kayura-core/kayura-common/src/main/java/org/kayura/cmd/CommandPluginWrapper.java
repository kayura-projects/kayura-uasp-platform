/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
 -----------------------------------------------------------------------------*/
package org.kayura.cmd;

import java.util.Arrays;

@SuppressWarnings({"ClassCanBeRecord"})
public class CommandPluginWrapper implements Comparable<CommandPluginWrapper> {

  private final Class<?>[] suppers;
  private final CommandPlugin plugin;
  private final int priority;

  public CommandPluginWrapper(CommandPlugin plugin, Class<?>[] suppers, int priority) {
    this.plugin = plugin;
    this.suppers = suppers;
    this.priority = priority;
  }

  public boolean hasSupport(Class<?> commandClass) {
    return suppers == null || Arrays.asList(suppers).contains(commandClass);
  }

  public <R> R invoke(Command command, CommandHandlerWrapper pluginWrapper) {
    return plugin.invoke(command, pluginWrapper);
  }

  @Override
  public int compareTo(CommandPluginWrapper other) {
    return Integer.compare(priority, other.priority);
  }

  public Class<?>[] getSuppers() {
    return suppers;
  }

  public CommandPlugin getPlugin() {
    return plugin;
  }

  public int getPriority() {
    return priority;
  }
}
