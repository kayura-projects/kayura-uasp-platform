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
