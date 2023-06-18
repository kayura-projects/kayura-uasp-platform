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

package org.kayura.mybatis.mapper.wrapper;

import org.kayura.mybatis.mapper.wrapper.segments.NormalSegmentList;
import org.kayura.mybatis.mapper.wrapper.support.Getter;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings({"unchecked", "rawtypes"})
public class LambdaWhereWrapper<T> extends AbstractWhereWrapper<Getter<T>> {

  public LambdaWhereWrapper(Class<?> entityClass) {
    super(entityClass);
  }

  public LambdaWhereWrapper(Class<?> entityClass, AtomicInteger paramSeq,
                            Map<String, Object> paramNameValuePairs, NormalSegmentList normal) {
    super(entityClass);
    this.paramSeq = paramSeq;
    this.paramValues = paramNameValuePairs;
    this.normal = normal;
  }

  @Override
  protected AbstractWhereWrapper<Getter<T>> nestedInstance() {
    return new LambdaWhereWrapper(this.entityClass, paramSeq, paramValues, new NormalSegmentList());
  }

}
