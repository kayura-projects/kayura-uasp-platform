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
