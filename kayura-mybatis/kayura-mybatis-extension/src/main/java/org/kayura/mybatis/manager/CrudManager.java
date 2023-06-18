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
