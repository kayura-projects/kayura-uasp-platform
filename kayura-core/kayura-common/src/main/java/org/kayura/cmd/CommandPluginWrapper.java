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
