/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
