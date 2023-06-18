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

package org.kayura.mybatis.manager;

import org.kayura.mybatis.mapper.wrapper.LambdaUpdateWrapper;
import org.kayura.mybatis.mapper.wrapper.LambdaWhereWrapper;
import org.kayura.mybatis.mapper.wrapper.Wrapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public interface CrudManager<T> extends SelectManager<T> {

  T selectById(Serializable id);

  boolean existByValue(String property, Object value);

  List<T> selectByIds(Collection<? extends Serializable> ids);

  boolean insertOne(T entity);

  default boolean insertBatch(Collection<T> entities) {
    return insertBatch(entities, 500);
  }

  boolean insertBatch(Collection<T> entities, int batchSize);


  boolean saveOrUpdate(T entity);

  default boolean saveOrUpdateBatch(Collection<T> entities) {
    return saveOrUpdateBatch(entities, 500);
  }

  boolean saveOrUpdateBatch(Collection<T> entities, int batchSize);

  boolean updateById(Serializable id, T entity);

  default boolean updateBatchById(Collection<T> entities) {
    return updateBatchById(entities, 500);
  }

  boolean updateBatchById(Collection<T> entities, int batchSize);

  boolean updateByWhere(Wrapper updateWrapper);

  boolean updateByWhere(Consumer<LambdaUpdateWrapper<T>> consumer);

  boolean deleteById(Serializable id);

  boolean deleteByIds(Collection<? extends Serializable> ids);

  boolean deleteByWhere(Wrapper deleteWrapper);

  boolean deleteByWhere(Consumer<LambdaWhereWrapper<T>> consumer);

}
