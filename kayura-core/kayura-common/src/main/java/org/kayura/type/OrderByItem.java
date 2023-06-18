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

package org.kayura.type;

public class OrderByItem {

  private String order;
  private String direction;

  public static OrderByItem create() {
    return new OrderByItem();
  }

  public static OrderByItem of(String order, String direction) {
    return new OrderByItem(order, direction);
  }

  public OrderByItem() {
  }

  public OrderByItem(String order, String direction) {
    this.order = order;
    this.direction = direction;
  }

  public boolean hasAsc() {
    return !hasDesc();
  }

  public boolean hasDesc() {
    return "DESC".equalsIgnoreCase(this.direction);
  }

  public String getOrder() {
    return order;
  }

  public OrderByItem setOrder(String order) {
    this.order = order;
    return this;
  }

  public String getDirection() {
    return direction;
  }

  public OrderByItem setDirection(String direction) {
    this.direction = direction;
    return this;
  }
}
