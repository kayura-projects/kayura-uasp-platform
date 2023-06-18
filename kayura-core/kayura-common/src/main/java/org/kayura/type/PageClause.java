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
