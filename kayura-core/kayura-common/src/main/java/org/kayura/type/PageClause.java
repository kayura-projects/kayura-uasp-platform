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
