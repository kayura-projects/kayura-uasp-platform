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
