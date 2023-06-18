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

package org.kayura.mybatis.mapper.wrapper;

import org.kayura.mybatis.mapper.wrapper.segments.SetSqlSegmentList;
import org.kayura.mybatis.mapper.wrapper.support.Update;
import org.kayura.mybatis.toolkit.StringPool;

import java.util.Arrays;
import java.util.Map;

import static org.kayura.mybatis.mapper.segments.SqlKeyword.EQ;

public abstract class AbstractUpdateWrapper<R> extends AbstractWrapper<AbstractUpdateWrapper<R>, R>
  implements Update<AbstractUpdateWrapper<R>, R>, StringPool {

  private final SetSqlSegmentList setSegments = new SetSqlSegmentList();

  public AbstractUpdateWrapper(Class<?> entityClass) {
    super(entityClass);
    super.initNeed();
  }

  @Override
  public AbstractUpdateWrapper<R> setAll(Map<String, Object> updateSet) {
    updateSet.forEach((k, v) -> {
      if (this.columnMap.containsKey(k)) {
        doSet(() -> columnNameToFieldName(k), EQ, () -> formatSql("{0}", v));
      }
    });
    return this;
  }

  @Override
  public AbstractUpdateWrapper<R> set(R column, Object value) {
    return doSet(() -> columnToFieldName(column), EQ, () -> formatSql("{0}", value));
  }

  @Override
  public AbstractUpdateWrapper<R> set(R column) {
    return doSet(() -> columnToFieldName(column), EQ, () -> formatSql("NULL"));
  }

  protected AbstractUpdateWrapper<R> doSet(SqlSegment... sqlSegments) {
    this.setSegments.addAll(Arrays.asList(sqlSegments));
    return this;
  }

  public String getSqlSet() {
    return this.setSegments.getSqlSegment();
  }

}
