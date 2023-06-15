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
