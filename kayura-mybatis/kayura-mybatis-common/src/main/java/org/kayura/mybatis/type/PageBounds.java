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

/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.mybatis.type;

import org.kayura.type.PageClause;
import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;

/**
 * @author 夏亮（liangxia@live.com）
 */
public class PageBounds extends RowBounds implements Serializable {

  private static final long serialVersionUID = 1L;

  protected int page;
  protected int limit;
  protected String orderBy;

  public PageBounds(int page, int limit) {
    this.page = page;
    this.limit = limit;
  }

  public PageBounds(PageClause pageClause) {
    this.page = pageClause.getPage();
    this.limit = pageClause.getRows();
  }

  public void setOrderBy(String orderByClause) {
    this.orderBy = orderByClause;
  }

  public int getOffset() {
    if (page >= 1) {
      return (page - 1) * limit;
    }
    return 0;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("PageBounds{");
    sb.append("page=").append(page);
    sb.append(", limit=").append(limit);
    sb.append(", orders=").append(orderBy);
    sb.append('}');
    return sb.toString();
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  @Override
  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public String getOrderBy() {
    return orderBy;
  }
}
