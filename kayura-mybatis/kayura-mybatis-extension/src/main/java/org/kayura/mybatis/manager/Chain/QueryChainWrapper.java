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
