/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
