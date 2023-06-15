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
