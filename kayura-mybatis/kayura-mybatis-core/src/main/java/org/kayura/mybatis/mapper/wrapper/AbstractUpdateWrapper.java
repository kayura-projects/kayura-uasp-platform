/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
