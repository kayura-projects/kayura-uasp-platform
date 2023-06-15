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
