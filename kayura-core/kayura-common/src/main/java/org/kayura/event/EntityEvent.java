package org.kayura.event;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

public class EntityEvent extends Event {

  private final EventTypes type;
  private Object entity;
  private Collection<? extends Serializable> businessKeys;

  protected EntityEvent(Object source, EventTypes type) {
    super(source);
    this.type = type;
  }

  public static EntityEvent preCreate(Object source, Object entity) {
    EntityEvent event = new EntityEvent(source, EventTypes.PreCreate);
    event.entity = entity;
    return event;
  }

  public static EntityEvent created(Object source, Object entity) {
    EntityEvent event = new EntityEvent(source, EventTypes.Created);
    event.entity = entity;
    return event;
  }

  public static EntityEvent preUpdate(Object source, Object entity) {
    EntityEvent event = new EntityEvent(source, EventTypes.PreUpdate);
    event.entity = entity;
    return event;
  }

  public static EntityEvent updated(Object source, Object entity) {
    EntityEvent event = new EntityEvent(source, EventTypes.Updated);
    event.entity = entity;
    return event;
  }

  public static EntityEvent preDelete(Object source, Serializable businessKey) {
    EntityEvent event = new EntityEvent(source, EventTypes.PreDelete);
    event.businessKeys = Collections.singletonList(businessKey);
    return event;
  }

  public static EntityEvent preDelete(Object source, Collection<? extends Serializable> businessKeys) {
    EntityEvent event = new EntityEvent(source, EventTypes.PreDelete);
    event.businessKeys = businessKeys;
    return event;
  }

  public static EntityEvent deleted(Object source, Serializable businessKey) {
    EntityEvent event = new EntityEvent(source, EventTypes.Deleted);
    event.businessKeys = Collections.singletonList(businessKey);
    return event;
  }

  public static EntityEvent deleted(Object source, Collection<? extends Serializable> businessKeys) {
    EntityEvent event = new EntityEvent(source, EventTypes.Deleted);
    event.businessKeys = businessKeys;
    return event;
  }

  public EventTypes getType() {
    return type;
  }

  public Object getEntity() {
    return entity;
  }

  public Collection<? extends Serializable> getBusinessKeys() {
    return businessKeys;
  }

}
