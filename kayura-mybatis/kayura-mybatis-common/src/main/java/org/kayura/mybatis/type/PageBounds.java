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
