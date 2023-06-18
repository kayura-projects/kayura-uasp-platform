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
