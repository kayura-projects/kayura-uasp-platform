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
