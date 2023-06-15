/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ 版权所属 2019 Liang.Xia 及 原作者
 ~ 您可以在 Apache License 2.0 版下获得许可副本，
 ~ 同时必须保证分发的本软件是在“原始”的基础上分发的。
 ~ 除非适用法律要求或书面同意。
 ~
 ~ http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ 请参阅许可证中控制权限和限制的特定语言。
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

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
