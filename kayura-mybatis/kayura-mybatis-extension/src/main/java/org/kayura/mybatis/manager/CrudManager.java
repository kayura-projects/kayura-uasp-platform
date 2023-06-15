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
