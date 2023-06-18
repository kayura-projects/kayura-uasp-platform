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

package org.kayura.mybatis.manager.impl;

import org.kayura.mybatis.mapper.SelectMapper;
import org.kayura.mybatis.mapper.metadata.EntityHelper;
import org.kayura.mybatis.mapper.metadata.EntityInfo;
import org.kayura.mybatis.mapper.wrapper.Wrapper;
import org.kayura.mybatis.manager.Chain.LambdaQueryChainWrapper;
import org.kayura.mybatis.manager.Chain.QueryChainWrapper;
import org.kayura.mybatis.manager.SelectManager;
import org.kayura.mybatis.toolkit.ReflectKit;
import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.utils.Assert;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@SuppressWarnings({"unchecked", "rawtypes"})
public abstract class SelectManagerImpl<M extends SelectMapper<T>, T> implements SelectManager<T> {

  protected M baseMapper;

  protected SelectManagerImpl(M baseMapper) {
    this.baseMapper = baseMapper;
  }

  protected Class<T> getEntityClass() {
    return (Class<T>) ReflectKit.getSuperClassGenericType(getClass(), 1);
  }

  public QueryChainWrapper queryWrapper() {
    return new QueryChainWrapper(baseMapper, getEntityClass());
  }

  public LambdaQueryChainWrapper<T> lambdaQueryWrapper() {
    return new LambdaQueryChainWrapper(baseMapper, getEntityClass());
  }

  protected EntityInfo getEntityInfo() {
    EntityInfo entityInfo = EntityHelper.getEntityInfo(getEntityClass());
    Assert.notNull(entityInfo, "未找到实体信息元数据。");
    return entityInfo;
  }

  protected String sqlStatement(String sqlMethod) {
    return EntityHelper.getEntityInfo(getEntityClass()).getSqlStatement(sqlMethod);
  }

  public List<T> selectList(Wrapper queryWrapper) {
    return baseMapper.selectList(queryWrapper);
  }

  public List<T> selectList(Consumer<LambdaQueryChainWrapper<T>> consumer) {
    LambdaQueryChainWrapper<T> queryWrapper = lambdaQueryWrapper();
    consumer.accept(queryWrapper);
    return baseMapper.selectList(queryWrapper);
  }

  public List<Map<String, Object>> selectMaps(Wrapper queryWrapper) {
    return baseMapper.selectMaps(queryWrapper);
  }

  public List<Map<String, Object>> selectMaps(Consumer<LambdaQueryChainWrapper<T>> consumer) {
    LambdaQueryChainWrapper<T> queryWrapper = lambdaQueryWrapper();
    consumer.accept(queryWrapper);
    return baseMapper.selectMaps(queryWrapper);
  }

  public int selectCount(Wrapper queryWrapper) {
    return baseMapper.selectCount(queryWrapper);
  }

  public int selectCount(Consumer<LambdaQueryChainWrapper<T>> consumer) {
    LambdaQueryChainWrapper<T> queryWrapper = lambdaQueryWrapper();
    consumer.accept(queryWrapper);
    return baseMapper.selectCount(queryWrapper);
  }

  public T selectOne(Wrapper queryWrapper) {
    return baseMapper.selectOne(queryWrapper);
  }

  public T selectOne(Consumer<LambdaQueryChainWrapper<T>> consumer) {
    LambdaQueryChainWrapper<T> queryWrapper = lambdaQueryWrapper();
    consumer.accept(queryWrapper);
    return baseMapper.selectOne(queryWrapper);
  }

  public PageList<T> selectPage(Wrapper queryWrapper, PageBounds pageBounds) {
    return baseMapper.selectPage(queryWrapper, pageBounds);
  }

  public PageList<T> selectPage(Consumer<LambdaQueryChainWrapper<T>> consumer, PageClause pageClause) {
    LambdaQueryChainWrapper<T> queryWrapper = lambdaQueryWrapper();
    consumer.accept(queryWrapper);
    return baseMapper.selectPage(queryWrapper, queryWrapper.pageBounds(pageClause));
  }

  public PageList<Map<String, Object>> selectMapsPage(Wrapper queryWrapper, PageBounds pageBounds) {
    return baseMapper.selectMapsPage(queryWrapper, pageBounds);
  }

  public PageList<Map<String, Object>> selectMapsPage(Consumer<LambdaQueryChainWrapper<T>> consumer, PageBounds pageBounds) {
    LambdaQueryChainWrapper<T> queryWrapper = lambdaQueryWrapper();
    consumer.accept(queryWrapper);
    return baseMapper.selectMapsPage(queryWrapper, pageBounds);
  }
}
