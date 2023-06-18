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

@SuppressWarnings("ClassCanBeRecord")
public class CommandInterceptorWrapper implements Comparable<CommandInterceptorWrapper> {

  private final Class<?>[] suppers;
  private final CommandInterceptor interceptor;
  private final int priority;

  public CommandInterceptorWrapper(CommandInterceptor interceptor, Class<?>[] suppers, int priority) {
    this.interceptor = interceptor;
    this.suppers = suppers;
    this.priority = priority;
  }

  public boolean hasSupport(Class<?> commandClass) {
    return suppers == null || Arrays.asList(suppers).contains(commandClass);
  }

  public void invokeBefore(CommandSession session) {
    interceptor.preHandle(session);
  }

  public void invokeAfter(CommandSession session) {
    interceptor.postHandle(session);
  }

  @Override
  public int compareTo(CommandInterceptorWrapper other) {
    return Integer.compare(priority, other.priority);
  }

  public Class<?>[] getSuppers() {
    return suppers;
  }

  public CommandInterceptor getInterceptor() {
    return interceptor;
  }

  public int getPriority() {
    return priority;
  }
}
