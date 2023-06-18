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

package org.kayura.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class DefaultEventGateway implements EventGateway {

  private final EventRegistry eventRegistry;
  private final Map<Class<? extends IEvent>, List<EventWrapper>> eventCache;

  public DefaultEventGateway(EventRegistry eventRegistry) {
    this.eventRegistry = eventRegistry;
    this.eventCache = new ConcurrentHashMap<>();
  }

  @Override
  public boolean isRegistered(Class<? extends IEvent> clazz) {
    return findEventWrappers(clazz).size() > 0;
  }

  @Override
  public void publish(IEvent event) {
    findEventWrappers(event.getClass()).forEach(wrapper -> {
      wrapper.invoke(event);
    });
  }

  protected List<EventWrapper> findEventWrappers(Class<? extends IEvent> eventClass) {

    return eventCache.computeIfAbsent(eventClass, (clazz) -> {

      List<EventWrapper> wrappers = new ArrayList<>();

      Class<?> thisClass = clazz;
      Map<Class<?>, CopyOnWriteArrayList<EventWrapper>> handlers = eventRegistry.getRegistry();
      while (thisClass != null) {
        if (handlers.containsKey(thisClass)) {
          wrappers.addAll(handlers.get(thisClass));
        }
        for (Class<?> interfaceType : thisClass.getInterfaces()) {
          if (handlers.containsKey(interfaceType)) {
            wrappers.addAll(handlers.get(interfaceType));
          }
        }
        thisClass = thisClass.getSuperclass();
      }

      return wrappers;
    });

  }

}
