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
