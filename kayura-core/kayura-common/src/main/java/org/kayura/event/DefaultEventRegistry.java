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

package org.kayura.event;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class DefaultEventRegistry implements EventRegistry {

  private final Map<Class<?>, CopyOnWriteArrayList<EventWrapper>> registry = new ConcurrentHashMap<>();

  @Override
  public <T extends IEvent> void register(Class<T> eventType, EventWrapper eventWrapper) {

    CopyOnWriteArrayList<EventWrapper> handlers =
      registry.computeIfAbsent(eventType, k -> new CopyOnWriteArrayList<>());
    handlers.add(eventWrapper);
  }

  @Override
  public <T extends IEvent> void unregister(Class<T> eventType, IEventListener IEventListener) {
    if (registry.containsKey(eventType)) {
      registry.get(eventType).removeIf(wrapper -> IEventListener.equals(wrapper.getListener()));
    }
  }

  @Override
  public Map<Class<?>, CopyOnWriteArrayList<EventWrapper>> getRegistry() {
    return registry;
  }
}
