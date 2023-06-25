/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
