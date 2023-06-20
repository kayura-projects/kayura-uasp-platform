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

  public <R> R invoke(ICommand command, CommandHandlerWrapper pluginWrapper) {
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
