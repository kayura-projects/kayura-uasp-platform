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
