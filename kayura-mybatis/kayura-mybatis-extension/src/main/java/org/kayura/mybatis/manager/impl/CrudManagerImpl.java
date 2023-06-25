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

package org.kayura.mybatis.manager.impl;

import org.kayura.event.EntityEvent;
import org.kayura.event.EventGateway;
import org.kayura.mybatis.mapper.CrudMapper;
import org.kayura.mybatis.mapper.injector.SqlMethods;
import org.kayura.mybatis.mapper.metadata.EntityInfo;
import org.kayura.mybatis.mapper.wrapper.*;
import org.kayura.mybatis.manager.CrudManager;
import org.kayura.mybatis.toolkit.Constants;
import org.kayura.mybatis.toolkit.GlobalUtils;
import org.kayura.mybatis.toolkit.ReflectKit;
import org.kayura.nextid.IdGenerator;
import org.kayura.nextid.NextIdManager;
import org.kayura.nextid.NextIdTypes;
import org.kayura.nextid.SnowflakeIdGenerator;
import org.kayura.utils.Assert;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public abstract class CrudManagerImpl<M extends CrudMapper<T>, T> extends SelectManagerImpl<M, T>
  implements CrudManager<T>, ApplicationContextAware, InitializingBean {

  protected ApplicationContext applicationContext;
  protected NextIdManager nextIdManager;
  protected IdGenerator idGenerator;
  protected EventGateway eventGateway;

  protected CrudManagerImpl(M baseMapper) {
    super(baseMapper);
  }

  @Override
  public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  @Override
  public void afterPropertiesSet() {

    // nextIdManager
    this.nextIdManager = this.applicationContext.getBean(NextIdManager.class);

    // eventGateway
    this.eventGateway = this.applicationContext.getBean(EventGateway.class);

    // idGenerator
    EntityInfo entityInfo = getEntityInfo();
    switch (entityInfo.getIdGenType()) {
      case UUID -> idGenerator = this.nextIdManager.get(NextIdTypes.UUID);
      case SNOWFLAKE -> idGenerator = this.nextIdManager.get(NextIdTypes.SNOWFLAKE);
    }
    if (this.idGenerator == null) {
      this.idGenerator = SnowflakeIdGenerator.INSTANCE;
    }
  }

  public String nextId() {
    return this.idGenerator.nextId();
  }

  protected SqlSession openSqlSessionBatch() {
    return GlobalUtils.currentSessionFactory(getEntityClass()).openSession(ExecutorType.BATCH);
  }

  protected void closeSqlSession(SqlSession sqlSession) {
    SqlSessionUtils.closeSqlSession(sqlSession, GlobalUtils.currentSessionFactory(getEntityClass()));
  }

  @Override
  public T selectById(Serializable id) {
    return baseMapper.selectById(id);
  }

  @Override
  public boolean existByValue(String property, Object value) {
    QueryWrapper qw = QueryWrapper.create(getEntityClass());
    qw.eq(property, value);
    return baseMapper.selectCount(qw) > 0;
  }

  @Override
  public List<T> selectByIds(Collection<? extends Serializable> ids) {
    return baseMapper.selectByIds(ids);
  }

  @Override
  public boolean insertOne(T entity) {
    eventGateway.publish(EntityEvent.preCreate(this, entity));
    int count = baseMapper.insertOne(entity);
    eventGateway.publish(EntityEvent.created(this, entity));
    return count > 0;
  }

  public boolean insertBatch(Collection<T> entities, int batchSize) {
    String insertOne = sqlStatement(SqlMethods.insertOne);
    try (SqlSession sqlSession = openSqlSessionBatch()) {
      int i = 0;
      for (T entity : entities) {
        sqlSession.insert(insertOne, entity);
        if (i >= 1 && i % batchSize == 0) {
          sqlSession.flushStatements();
        }
        i++;
      }
      sqlSession.flushStatements();
    }
    return true;
  }

  public boolean saveOrUpdate(T entity) {
    Assert.notNull(entity, "不能保存一个空实体。");
    EntityInfo entityInfo = getEntityInfo();
    String idProperty = entityInfo.getIdProperty();
    Assert.hasText(idProperty, "实体未定义主键属性，无法执行操作。");
    Object idValue = ReflectKit.getMethodValue(entity, idProperty);
    return StringUtils.checkValNull(idValue) || existByValue(idProperty, idValue)
      ? insertOne(entity)
      : updateById((Serializable) idValue, entity);
  }

  public boolean saveOrUpdateBatch(Collection<T> entities, int batchSize) {

    Assert.notEmpty(entities, "不能保存一个空实体集合。");
    EntityInfo entityInfo = getEntityInfo();
    String idProperty = entityInfo.getIdProperty();
    Assert.hasText(idProperty, "实体未定义主键属性，无法执行操作。");

    try (SqlSession sqlSession = openSqlSessionBatch()) {
      int i = 0;
      for (T entity : entities) {
        Object idValue = ReflectKit.getMethodValue(entity, idProperty);
        if (StringUtils.checkValNull(idValue) || existByValue(idProperty, idValue)) {
          String insertOne = sqlStatement(SqlMethods.insertOne);
          sqlSession.insert(insertOne, entity);
        } else {
          MapperMethod.ParamMap<Object> param = new MapperMethod.ParamMap<>();
          param.put(Constants.ENTITY, entity);
          sqlSession.update(sqlStatement(SqlMethods.updateById), param);
        }
        if (i >= 1 && i % batchSize == 0) {
          sqlSession.flushStatements();
        }
        i++;
      }
      sqlSession.flushStatements();
    }

    return true;
  }

  public boolean updateById(Serializable idValue, T entity) {
    String idProperty = getEntityInfo().getIdProperty();
    Assert.hasText(idProperty, "实体未定义主键属性，无法执行操作。");
    ReflectKit.setFieldValue(idProperty, entity, idValue);
    eventGateway.publish(EntityEvent.preUpdate(this, entity));
    int count = baseMapper.updateById(entity);
    eventGateway.publish(EntityEvent.updated(this, entity));
    return count > 0;
  }

  public boolean updateBatchById(Collection<T> entities, int batchSize) {
    try (SqlSession sqlSession = openSqlSessionBatch()) {
      int i = 0;
      for (T entity : entities) {
        MapperMethod.ParamMap<Object> param = new MapperMethod.ParamMap<>();
        param.put(Constants.ENTITY, entity);
        sqlSession.update(sqlStatement(SqlMethods.updateById), param);
        if (i >= 1 && i % batchSize == 0) {
          sqlSession.flushStatements();
        }
        i++;
      }
      sqlSession.flushStatements();
    }
    return true;
  }

  public boolean updateByWhere(Wrapper updateWrapper) {
    return baseMapper.updateByWhere(updateWrapper) > 0;
  }

  public boolean updateByWhere(Consumer<LambdaUpdateWrapper<T>> consumer) {
    LambdaUpdateWrapper<T> wrapper = UpdateWrapper.createLambda(getEntityClass());
    consumer.accept(wrapper);
    return baseMapper.updateByWhere(wrapper) > 0;
  }

  public boolean deleteById(Serializable id) {
    eventGateway.publish(EntityEvent.preDelete(this, id));
    int count = baseMapper.deleteById(id);
    eventGateway.publish(EntityEvent.deleted(this, id));
    return count > 0;
  }

  public boolean deleteByIds(Collection<? extends Serializable> ids) {
    if (ids.size() > 500) {
      List<? extends Collection<? extends Serializable>> splitList = CollectionUtils.splitList(ids, 500);
      for (Collection<? extends Serializable> collection : splitList) {
        eventGateway.publish(EntityEvent.preDelete(this, collection));
        baseMapper.deleteByIds(collection);
        eventGateway.publish(EntityEvent.preDelete(this, collection));
      }
      return true;
    }
    int count = baseMapper.deleteByIds(ids);
    return count > 0;
  }

  public boolean deleteByWhere(Wrapper deleteWrapper) {
    return baseMapper.deleteByWhere(deleteWrapper) > 0;
  }

  public boolean deleteByWhere(Consumer<LambdaWhereWrapper<T>> consumer) {
    LambdaWhereWrapper<T> whereWrapper = WhereWrapper.createLambda(getEntityClass());
    consumer.accept(whereWrapper);
    return baseMapper.deleteByWhere(whereWrapper) > 0;
  }

}
