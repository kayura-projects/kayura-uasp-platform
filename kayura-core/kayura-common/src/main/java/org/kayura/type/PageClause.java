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

/**
 * 用于支持查询分页的请求参数。
 */
public class PageClause {

  private Integer page;
  private Integer rows;
  private OrderByClause orderby;

  public PageClause() {
  }

  public PageClause(Integer page, Integer rows) {
    this.page = page;
    this.rows = rows;
  }

  public PageClause(Integer page, Integer rows, String orderby) {
    this.page = page;
    this.rows = rows;
    this.orderby = OrderByClause.resolveForNative(orderby);
  }

  public PageClause(Integer page, Integer rows, OrderByClause orderby) {
    this.page = page;
    this.rows = rows;
    this.orderby = orderby;
  }

  public static PageClause of(int page, int limit) {
    return new PageClause(page, limit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("PageClause{");
    if (page != null) {
      sb.append("page=").append(page);
    }
    sb.append(", rows=").append(rows);
    if (orderby != null) {
      sb.append(", orderby=").append(orderby.genNativeSql());
    }
    sb.append('}');
    return sb.toString();
  }

  public boolean hasPage() {
    return page != null;
  }

  public Integer getPage() {
    return page;
  }

  public PageClause setPage(Integer page) {
    this.page = page;
    return this;
  }

  public Integer getRows() {
    return rows;
  }

  public PageClause setRows(Integer rows) {
    this.rows = rows;
    return this;
  }

  public OrderByClause getOrderby() {
    return orderby;
  }

  public PageClause setOrderby(OrderByClause orderby) {
    this.orderby = orderby;
    return this;
  }

}
