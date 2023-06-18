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

package org.kayura.mybatis.manager.Chain;

import org.kayura.mybatis.mapper.SelectMapper;
import org.kayura.mybatis.mapper.wrapper.AbstractQueryWrapper;
import org.kayura.mybatis.mapper.wrapper.AbstractWhereWrapper;
import org.kayura.mybatis.mapper.wrapper.WhereWrapper;
import org.kayura.mybatis.mapper.wrapper.Wrapper;
import org.kayura.mybatis.mapper.wrapper.segments.NormalSegmentList;

public class QueryChainWrapper<T> extends AbstractQueryWrapper<String> implements ChainQuery<T> {

  protected SelectMapper<T> baseMapper;

  public QueryChainWrapper(SelectMapper<T> baseMapper, Class<?> entityClass) {
    super(entityClass);
    this.baseMapper = baseMapper;
  }

  @Override
  protected AbstractWhereWrapper<String> nestedInstance() {
    return new WhereWrapper(this.entityClass, paramSeq, paramValues, new NormalSegmentList());
  }

  @Override
  public SelectMapper<T> getBaseMapper() {
    return baseMapper;
  }

  @Override
  public Wrapper getWrapper() {
    return this;
  }
}
