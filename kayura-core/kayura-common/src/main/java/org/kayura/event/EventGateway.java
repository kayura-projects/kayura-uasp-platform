package org.kayura.event;

public interface EventGateway {

  boolean isRegistered(Class<? extends IEvent> clazz);

  void publish(IEvent message);
}
