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
