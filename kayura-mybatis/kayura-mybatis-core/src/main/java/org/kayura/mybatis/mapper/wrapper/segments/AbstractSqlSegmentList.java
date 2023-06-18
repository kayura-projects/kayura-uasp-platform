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
