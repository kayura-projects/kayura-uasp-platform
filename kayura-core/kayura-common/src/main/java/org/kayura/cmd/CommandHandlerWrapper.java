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

@SuppressWarnings({"ClassCanBeRecord", "rawtypes"})
public final class CommandHandlerWrapper implements Comparable<CommandHandlerWrapper> {

  private final CommandHandler handler;
  private final int priority;

  public CommandHandlerWrapper(CommandHandler handler, int priority) {
    this.handler = handler;
    this.priority = priority;
  }

  @SuppressWarnings("unchecked")
  public <R> R invoke(ICommand command) {
    return (R) handler.execute(command);
  }

  @Override
  public int compareTo(CommandHandlerWrapper other) {
    return Integer.compare(priority, other.priority);
  }

  public CommandHandler getHandler() {
    return this.handler;
  }

  public int getPriority() {
    return this.priority;
  }
}