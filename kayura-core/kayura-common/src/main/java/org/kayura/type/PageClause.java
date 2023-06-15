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
