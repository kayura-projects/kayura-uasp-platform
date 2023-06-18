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
