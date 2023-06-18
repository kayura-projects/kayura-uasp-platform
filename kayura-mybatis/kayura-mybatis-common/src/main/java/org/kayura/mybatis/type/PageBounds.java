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

package org.kayura.mybatis.type;

import org.kayura.type.PageClause;
import org.apache.ibatis.session.RowBounds;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author 夏亮（liangxia@live.com）
 */
public class PageBounds extends RowBounds implements Serializable {

  @Serial
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
