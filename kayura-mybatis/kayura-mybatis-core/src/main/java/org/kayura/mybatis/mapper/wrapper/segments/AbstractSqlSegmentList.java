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

package org.kayura.mybatis.mapper.wrapper.segments;

import org.kayura.mybatis.mapper.wrapper.SqlSegment;
import org.kayura.mybatis.toolkit.StringKit;
import org.kayura.mybatis.toolkit.StringPool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractSqlSegmentList extends ArrayList<SqlSegment> implements SqlSegment, StringPool {

  protected SqlSegment lastValue = null;
  protected boolean flushLastValue = false;
  private String sqlSegment = EMPTY;
  private boolean cacheSqlSegment = true;

  @Override
  public boolean addAll(Collection<? extends SqlSegment> c) {
    List<SqlSegment> list = new ArrayList<>(c);
    boolean goon = transformList(list, list.get(0));
    if (goon) {
      cacheSqlSegment = false;
      if (flushLastValue) {
        this.flushLastValue(list);
      }
      return super.addAll(list);
    }
    return false;
  }

  protected abstract boolean transformList(List<SqlSegment> list, SqlSegment firstSegment);

  private void flushLastValue(List<? extends SqlSegment> list) {
    lastValue = list.get(list.size() - 1);
  }

  void removeFlushLast() {
    remove(size() - 1);
    flushLastValue(this);
  }

  @Override
  public String getSqlSegment() {
    if (cacheSqlSegment) {
      return StringKit.isNoneBlank(sqlSegment) ? sqlSegment : null;
    }
    cacheSqlSegment = true;
    sqlSegment = childrenSqlSegment();
    return StringKit.isNoneBlank(sqlSegment) ? sqlSegment : null;
  }

  protected abstract String childrenSqlSegment();

}
