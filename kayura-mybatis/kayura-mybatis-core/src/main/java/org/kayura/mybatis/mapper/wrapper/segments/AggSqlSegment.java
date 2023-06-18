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

import org.kayura.mybatis.mapper.wrapper.AbstractWrapper;
import org.kayura.mybatis.mapper.wrapper.SqlSegment;

import java.util.Arrays;

@SuppressWarnings({"unchecked", "rawtypes"})
public class AggSqlSegment<R> implements SqlSegment {

  private final AbstractWrapper wrapper;
  private final String funcName;
  private final SimpleSegmentList simple = new SimpleSegmentList();

  public AggSqlSegment(String funcName, AbstractWrapper wrapper) {
    this.funcName = funcName;
    this.wrapper = wrapper;
  }

  @Override
  public String getSqlSegment() {
    return simple.getSqlSegment();
  }

  public AggSqlSegment<R> select(R column) {
    return doIt(() -> funcName + "(" + this.wrapper.columnToFieldName(column) + ")");
  }

  public AggSqlSegment<R> as(String alias) {
    return doIt(() -> "AS", () -> alias);
  }

  protected AggSqlSegment<R> doIt(SqlSegment... sqlSegments) {
    this.simple.addAll(Arrays.asList(sqlSegments));
    return this;
  }

}
