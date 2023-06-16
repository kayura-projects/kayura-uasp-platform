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
